package desafio.itau.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private OffsetDateTime date;

    ErrorResponse(int status, String message){
        this.status = status;
        this.message = message;
        this.date = OffsetDateTime.now();
    }
}
