package desafio.itau.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class TransactionRequestDTO {
    //not null e valores nao podem ser menor que 0
    @NotNull(message = "Transaction value cannot be null")
    private Double valor;

    @NotNull(message = "Transaction value cannot be null")
    private OffsetDateTime dataHora;
}
