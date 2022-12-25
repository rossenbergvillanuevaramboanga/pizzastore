package it.prova.pizzastore.dto;

import it.prova.pizzastore.model.Ruolo;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuoloDTO {

    private Long id;
    private String descrizione;
    private String codice;

    public static RuoloDTO buildRuoloDTOFromModel(Ruolo ruoloModel) {
        return new RuoloDTO(ruoloModel.getId(), ruoloModel.getDescrizione(), ruoloModel.getCodice());
    }

    public static List<RuoloDTO> createRuoloDTOListFromModelSet(Set<Ruolo> modelListInput) {
        return modelListInput.stream().map(ruoloEntity -> {
            return RuoloDTO.buildRuoloDTOFromModel(ruoloEntity);
        }).collect(Collectors.toList());
    }

    public static List<RuoloDTO> createRuoloDTOListFromModelList(List<Ruolo> modelListInput) {
        return modelListInput.stream().map(ruoloEntity -> {
            return RuoloDTO.buildRuoloDTOFromModel(ruoloEntity);
        }).collect(Collectors.toList());
    }
}
