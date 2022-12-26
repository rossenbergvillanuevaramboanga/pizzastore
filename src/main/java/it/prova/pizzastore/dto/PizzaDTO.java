package it.prova.pizzastore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.pizzastore.model.Pizza;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PizzaDTO {


    private Long id;
    @NotBlank(message = "{descrizione.notblank}")
    private String descrizione;
    @NotBlank(message = "{ingredienti.notnull}")
    private String ingredienti;
    @NotNull(message = "{prezzoBase.notnull}")
    private Integer prezzoBase;
    @NotNull(message = "{attivo.notnull}")
    private Boolean attivo;

    public Pizza buildPizzaModel(){
        Pizza result = Pizza.builder()
                .id(id)
                .descrizione(descrizione)
                .prezzoBase(prezzoBase)
                .attivo(attivo)
                .build();
        return result;
    }

    public static PizzaDTO buildPizzaDTOFromModel(Pizza pizzaModel) {
        return new PizzaDTO(pizzaModel.getId(),pizzaModel.getDescrizione(), pizzaModel.getIngredienti(), pizzaModel.getPrezzoBase(), pizzaModel.getAttivo());
    }

    public static List<PizzaDTO> createPizzaDTOListFromModelSet(Set<Pizza> modelListInput){
        return modelListInput.stream().map(PizzaDTO::buildPizzaDTOFromModel).collect(Collectors.toList());
    }

    public static List<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput){
        return modelListInput.stream().map(PizzaDTO::buildPizzaDTOFromModel).collect(Collectors.toList());
    }

    public static Set<PizzaDTO> createPizzaDTOSetFromModelSet(Set<Pizza> modelListInput){
        return modelListInput.stream().map(PizzaDTO::buildPizzaDTOFromModel).collect(Collectors.toSet());
    }
}
