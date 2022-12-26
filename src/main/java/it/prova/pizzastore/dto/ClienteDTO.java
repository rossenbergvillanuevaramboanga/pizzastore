package it.prova.pizzastore.dto;

import it.prova.pizzastore.model.Cliente;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {


    private Long id;

    @NotBlank(message = "{nome.notblank}")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    private String cognome;

    @NotBlank(message = "{indirizzo.notblank}")
    private String indirizzo;

    private boolean attivo;

    public Cliente buildClidenteModel(){
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
                .attivo(clientModel.isAttivo())
                .build();

        return result;
    }



}
