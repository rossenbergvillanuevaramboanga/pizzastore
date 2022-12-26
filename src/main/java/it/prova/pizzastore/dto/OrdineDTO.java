package it.prova.pizzastore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
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

    private Set<PizzaDTO> pizze = new HashSet<PizzaDTO>(0);
    @NotNull(message = "{cliente.notnull}")
    private ClienteDTO cliente;
    @NotNull(message = "{fattorino.notnull}")
    private UtenteDTO fattorino;

    public Ordine buildOrdineModel(){
        Ordine result = Ordine.builder()
                .id(id)
                .data(data)
                .closed(closed)
                .codice(codice)
                .costo(costo).build();

        if(pizze != null)
        result.setPizze(pizze.stream().map(PizzaDTO::buildPizzaModel).collect(Collectors.toSet()));

        if (fattorino != null)
            result.setFattorino(Utente.builder().id(id).build());

        if (cliente != null)
            result.setCliente(Cliente.builder().id(id).build());

        return result;
    }

    public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel,boolean includePizze, boolean includeFattorino, boolean includeCliente ){
        OrdineDTO result = OrdineDTO.builder()
                .id(ordineModel.getId())
                .data(ordineModel.getData())
                .closed(ordineModel.getClosed())
                .costo(ordineModel.getCosto())
                .build();

        if (includePizze)
            result.setPizze( PizzaDTO.createPizzaDTOSetFromModelSet(ordineModel.getPizze()));

        if(includeFattorino)
            result.setFattorino(UtenteDTO.buildUtenteDTOFromModel(ordineModel.getFattorino()));

        if(includeCliente)
            result.setCliente(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente()));

        return result;
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelSet(Set<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
        }).collect(Collectors.toList());
    }

    public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput){
        return modelListInput.stream().map(ordine -> {
            return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
        }).collect(Collectors.toList());
    }
}
