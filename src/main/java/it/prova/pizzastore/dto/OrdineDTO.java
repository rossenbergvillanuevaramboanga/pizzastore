package it.prova.pizzastore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Utente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdineDTO {

    private Long id;

    private LocalDate data;

    private Boolean closed;
    @NotBlank(message = "{codice.notblank}")
    private String codice;
    @NotNull(message = "{costo.notnull}")
    private Integer costo;

    private Long[] pizzeIds;
    private Long clienteId;
    private Long fattorinoId;

    public Ordine buildOrdineModel(boolean includeIdPizze, boolean includeIdFattorino, boolean includeIdCliente){
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

        if (includeIdCliente && clienteId != null)
            result.setCliente(Cliente.builder().id(id).build());

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

        if(!ordineModel.getCliente().equals(null))
            result.clienteId = ordineModel.getCliente().getId();

        return result;
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelSet(Set<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine);
        }).collect(Collectors.toList());
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine);
        }).collect(Collectors.toList());
    }
}
