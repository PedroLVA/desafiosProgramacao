package desafio.itau.springboot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public class TransactionRequestDTO {
    //not null e valores nao podem ser menor que 0
    @NotNull

    private Double valor;

    @NotNull
    private OffsetDateTime dataHora;
}
