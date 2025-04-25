package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.TransactionRequestDTO;
import desafio.itau.springboot.model.Transaction;
import desafio.itau.springboot.service.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    private ResponseEntity<Void> createTransaciton(@Valid @RequestBody TransactionRequestDTO request){
        if(request.getDataHora().isAfter(OffsetDateTime.now())){
            //as requested in the challenge description
            return ResponseEntity.unprocessableEntity().build();
        }
        transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    private ResponseEntity<Void> clearTransactions(){
        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
