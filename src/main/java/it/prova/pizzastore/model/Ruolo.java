package it.prova.pizzastore.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ruolo")
public class Ruolo {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_PIZZAIOLO = "ROLE_PIZZAIOLO";
    public static final String ROLE_PROPRIETARIO = "ROLE_PROPRIETARIO";
    public static final String ROLE_FATTORINO = "ROLE_FATTORINO";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "codice")
    private String codice;
}
