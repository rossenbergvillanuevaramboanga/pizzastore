package it.prova.pizzastore.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "email")
    private String email;
    @Column(name = "dateCreated")
    private Date dateCreated;

    // se non uso questa annotation viene gestito come un intero
    @Enumerated(EnumType.STRING)
    private StatoUtente stato;

    @ManyToMany
    @JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
    private Set<Ruolo> ruoli = new HashSet<Ruolo>(0);

}
