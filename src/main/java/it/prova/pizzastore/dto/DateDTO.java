package it.prova.pizzastore.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DateDTO {

    private LocalDate dataInizio;

    private LocalDate dataFine;
}
