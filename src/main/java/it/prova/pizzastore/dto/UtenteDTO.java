package it.prova.pizzastore.dto;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtenteDTO {

    private Long id;

    @NotBlank(message = "{username.notblank}")
    @Size(min = 3, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
    private String username;

    @NotBlank(message = "{password.notblank}")
    @Size(min = 8, max = 15, message = "Il valore inserito deve essere lungo tra {min} e {max} caratteri")
    private String password;

    private String confermaPassword;

    @NotBlank(message = "{nome.notblank}")
    private String nome;

    @NotBlank(message = "{cognome.notblank}")
    private String cognome;

    @NotBlank(message = "{email.notblank}")
    private String email;

    private LocalDate dateCreated;

    private StatoUtente stato;

    private Long[] ruoliIds;

    public Utente buildUtenteModel(boolean includeIdRoles) {
        Utente result = Utente.builder()
                .id(id)
                .username(username)
                .password(password)
                .nome(nome)
                .cognome(cognome)
                .email(email)
                .dateCreated(dateCreated)
                .stato(stato).build();

        if (includeIdRoles && ruoliIds != null)
            result.setRuoli(Arrays.asList(ruoliIds).stream().map(id -> Ruolo.builder().id(id).build() ).collect(Collectors.toSet()));

        return result;
    }

    // niente password...
    public static UtenteDTO buildUtenteDTOFromModel(Utente utenteModel) {
        UtenteDTO result = UtenteDTO.builder()
                .id(utenteModel.getId())
                .username(utenteModel.getUsername())
                .nome(utenteModel.getNome())
                .cognome(utenteModel.getCognome())
                .stato(utenteModel.getStato())
                .build();

        if (!utenteModel.getRuoli().isEmpty())
            result.ruoliIds = utenteModel.getRuoli().stream().map(r -> r.getId()).collect(Collectors.toList())
                    .toArray(new Long[] {});

        return result;
    }
}
