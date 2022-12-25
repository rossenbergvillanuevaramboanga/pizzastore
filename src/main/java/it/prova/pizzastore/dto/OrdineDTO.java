package it.prova.pizzastore.dto;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Utente;
import lombok.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdineDTO {

    private Long id;
    private LocalDate data;
    private Boolean closed;
    private String codice;
    private Integer costo;
    private Long[] pizzeIds;
    private Long fattorinoId;

    public Ordine buildOrdineModel(boolean includeIdPizze, boolean includeIdFattorino){
        Ordine result = Ordine.builder()
                .id(id)
                .data(data)
                .closed(closed)
                .codice(codice)
                .costo(costo).build();

        if (includeIdPizze && pizzeIds != null)
            result.setPizze(Arrays.asList(pizzeIds).stream().map(id -> Pizza.builder().id(id).build() ).collect(Collectors.toSet()));

        if (includeIdFattorino && fattorinoId != null)
            result.setFattorino(Utente.builder().id(id).build());

        return result;
    }

    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel){
        OrdineDTO result = OrdineDTO.builder()
                .id(ordineModel.getId())
                .data(ordineModel.getData())
                .closed(ordineModel.getClosed())
                .costo(ordineModel.getCosto())
                .build();

        if (!ordineModel.getPizze().isEmpty())
            result.pizzeIds = ordineModel.getPizze().stream().map(r -> r.getId()).collect(Collectors.toList())
                    .toArray(new Long[] {});

        if(!ordineModel.getFattorino().equals(null))
            result.fattorinoId = ordineModel.getFattorino().getId();

        return result;
    }
}
