package desafio.itau.springboot.controller;

import desafio.itau.springboot.dto.StatisticsResponse;
import desafio.itau.springboot.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticsController {

    private final TransactionService transactionService;

    @GetMapping
    private ResponseEntity<StatisticsResponse> getStatistics(){
        return ResponseEntity.ok(new StatisticsResponse(transactionService.getStatistics()));
    }
}
