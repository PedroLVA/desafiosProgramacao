package desafio.itau.springboot.service;

import desafio.itau.springboot.dto.TransactionRequestDTO;
import desafio.itau.springboot.exception.FutureTransactionException;
import desafio.itau.springboot.exception.NegativeValueException;
import desafio.itau.springboot.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;


    @BeforeEach
    public void setup(){
        transactionService = new TransactionService();
    }

    @Test
    void addTransaction_Sucess() {
        Transaction transaction = createTransaction(100,  OffsetDateTime.parse("2025-04-28T15:11:16.789-03:00"));
        transactionService.addTransaction(transaction);
        assertEquals(1, transactionService.getTransactions().size());
        assertTrue(transactionService.getTransactions().contains(transaction));

    }

    @Test
    void clearTransactions() {
        Transaction transaction = createTransaction(100,  OffsetDateTime.parse("2025-04-28T15:11:16.789-03:00"));
        Transaction transaction1 = createTransaction(150,  OffsetDateTime.parse("2025-04-28T15:16:16.789-03:00"));

        transactionService.addTransaction(transaction);
        transactionService.addTransaction(transaction1);
        assertEquals(2, transactionService.getTransactions().size());

        transactionService.clearTransactions();

        assertTrue(transactionService.getTransactions().isEmpty());
    }

    @Test
    void getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        Transaction transaction = createTransaction(100,  now);
        Transaction transaction1 = createTransaction(100,  now);
        //average equals 100
        //min equals 100
        //max equals 100
        //sum equals 200

        transactionService.addTransaction(transaction);
        transactionService.addTransaction(transaction1);
        //assert that it was actually added
        assertEquals(2, transactionService.getTransactions().size());

        DoubleSummaryStatistics statistics = transactionService.getStatistics();

        assertEquals(100, statistics.getAverage());
        assertEquals(100, statistics.getMin());
        assertEquals(100, statistics.getMax());
        assertEquals(200, statistics.getSum());
        assertEquals(2, statistics.getCount());

    }

    @Test
    void getStatistics_FiltersOldTransactions() {

        Transaction oldTransaction = createTransaction(50, OffsetDateTime.now().minusSeconds(61));
        transactionService.addTransaction(oldTransaction);


        Transaction recentTransaction = createTransaction(100, OffsetDateTime.now());
        transactionService.addTransaction(recentTransaction);

        DoubleSummaryStatistics stats = transactionService.getStatistics();

        assertEquals(1, stats.getCount());
        assertEquals(100, stats.getSum());
    }

    @Test
    void getStatistics_EmptyQueue() {
        DoubleSummaryStatistics stats = transactionService.getStatistics();
        assertEquals(0, stats.getCount());
        assertEquals(0, stats.getSum());
        assertEquals(Double.POSITIVE_INFINITY, stats.getMin()); // Default for no elements
        assertEquals(Double.NEGATIVE_INFINITY, stats.getMax()); // Default for no elements
    }

    @Test
    void validateTransaction_ValidTransaction_NoExceptionThrown() {

        TransactionRequestDTO request = createTransactionRequestDTO(100, OffsetDateTime.now());


        assertDoesNotThrow(() -> transactionService.validateTransaction(request));
    }

    @Test
    void validateTransaction_ZeroAmount_NoExceptionThrown() {

        TransactionRequestDTO request = createTransactionRequestDTO(0, OffsetDateTime.now());


        assertDoesNotThrow(() -> transactionService.validateTransaction(request));
    }

    @Test
    void validateTransaction_NegativeAmount_NegativeValueExceptionThrown(){

        TransactionRequestDTO request = createTransactionRequestDTO(-10, OffsetDateTime.now());

        assertThrows(NegativeValueException.class, () -> transactionService.validateTransaction(request));
    }

    @Test
    void validateTransaction_FutureTimestamp_FutureTransactionExceptionThrown(){

        TransactionRequestDTO request = createTransactionRequestDTO(10, OffsetDateTime.now().plusHours(10));

        assertThrows(FutureTransactionException.class, () -> transactionService.validateTransaction(request));
    }



    // @Test
  //  void validateTransaction_ZeroAmount_returnTrue() {
        //valid transaction
      //  TransactionRequestDTO request = createTransactionRequestDTO(0,  OffsetDateTime.now());

        //assertTrue(transactionService.validateTransaction(request));

  //  }

   // @Test
  //  void validateTransaction_FutureTimestamp_ReturnsFalse() {
  //      TransactionRequestDTO request = createTransactionRequestDTO(100, OffsetDateTime.now().plusMinutes(1));
  //      assertFalse(transactionService.validateTransaction(request));
  //  }

    //@Test
  //  void validateTransaction_NegativeAmountAndFutureTimestamp_ReturnsFalse() {
   //     TransactionRequestDTO request = createTransactionRequestDTO(-100, OffsetDateTime.now().plusMinutes(1));
  //      assertFalse(transactionService.validateTransaction(request));
  //  }


    private Transaction createTransaction(double valor, OffsetDateTime dataHora){
        return new Transaction(valor, dataHora);
    }

    private TransactionRequestDTO createTransactionRequestDTO(double valor, OffsetDateTime dataHora){
        return new TransactionRequestDTO(valor, dataHora);
    }

}