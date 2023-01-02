package it.prova.pizzastore.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticheDTO {

    private Integer ricavi;
    private Integer costi;
    private Integer numeroOrdini;
    private Integer numeroPizze;
    private Set<ClienteDTO> clientiVirtuosi = new HashSet<ClienteDTO>(0);
}
