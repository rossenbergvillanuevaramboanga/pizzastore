package it.prova.pizzastore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.prova.pizzastore.model.Cliente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {


    private Long id;

    @NotBlank(message = "{nome.notblank}")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    private String cognome;

    @NotBlank(message = "{indirizzo.notblank}")
    private String indirizzo;

    private boolean attivo;

    public Cliente buildClienteModel(){
        Cliente result = Cliente.builder()
                .id(id)
                .nome(nome)
                .cognome(cognome)
                .indirizzo(indirizzo)
                .attivo(attivo)
                .build();

        return result;
    }

    public static ClienteDTO buildClienteDTOFromModel(Cliente clientModel){
        ClienteDTO result = ClienteDTO.builder()
                .id(clientModel.getId())
                .nome(clientModel.getNome())
                .cognome(clientModel.getCognome())
                .indirizzo(clientModel.getIndirizzo())
                .attivo(clientModel.getAttivo())
                .build();

        return result;
    }

    public static List<ClienteDTO> createClienteDTOListFromModelSet(Set<Cliente> modelListInput){
        return modelListInput.stream().map(cliente -> {
            return ClienteDTO.buildClienteDTOFromModel(cliente);
        }).collect(Collectors.toList());
    }

    public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput){
        return modelListInput.stream().map(cliente -> {
            return ClienteDTO.buildClienteDTOFromModel(cliente);
        }).collect(Collectors.toList());
    }

    public static Set<ClienteDTO> createClienteDTOSetFromModelList(List<Cliente> modelListInput){
        return modelListInput.stream().map(cliente -> {
            return ClienteDTO.buildClienteDTOFromModel(cliente);
        }).collect(Collectors.toSet());
    }



}
