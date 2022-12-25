package it.prova.pizzastore.dto;

import it.prova.pizzastore.model.Pizza;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {


    private Long id;
    private String descrizione;
    private String ingredienti;
    private Integer prezzoBase;
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
        return modelListInput.stream().map(pizza -> {
            return PizzaDTO.buildPizzaDTOFromModel(pizza);
        }).collect(Collectors.toList());
    }

    public static Set<PizzaDTO> createPizzaDTOListFromModelList(List<Pizza> modelListInput){
        return modelListInput.stream().map(pizza -> {
            return PizzaDTO.buildPizzaDTOFromModel(pizza);
        }).collect(Collectors.toSet());
    }
}
