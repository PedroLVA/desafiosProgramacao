package desafio.itau.springboot.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class Transaction {
    //Como tenho um all args constructor, nao preciso de setters

    private double valor;
    private OffsetDateTime data;
}
