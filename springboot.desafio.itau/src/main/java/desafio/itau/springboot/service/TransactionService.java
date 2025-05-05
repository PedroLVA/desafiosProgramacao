package desafio.itau.springboot.service;

import desafio.itau.springboot.dto.TransactionRequestDTO;
import desafio.itau.springboot.exception.ExpiredTransactionException;
import desafio.itau.springboot.exception.FutureTransactionException;
import desafio.itau.springboot.exception.NegativeValueException;
import desafio.itau.springboot.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Queue<Transaction> getTransactions() {
        return transactions;
    }

    public void clearTransactions() {
        transactions.clear();
    }

    public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();
        return transactions.stream()
                .filter(t -> t.getData().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::getValor)
                .summaryStatistics();
    }

    public void validateTransaction(TransactionRequestDTO request) {
        // Check if transaction is in the future
        if (request.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new FutureTransactionException("Transaction timestamp cannot be in the future");
        }

        // Check if transaction has a negative value
        if (request.getValor() < 0) {
            throw new NegativeValueException("Transaction value cannot be negative");
        }

        // You can add more validation rules here as needed
    }


}