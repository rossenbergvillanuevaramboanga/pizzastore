package it.prova.pizzastore.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dateCreated;

    // se non uso questa annotation viene gestito come un intero
    @Enumerated(EnumType.STRING)
    private StatoUtente stato;

    @ManyToMany
    @JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
    private Set<Ruolo> ruoli = new HashSet<Ruolo>(0);

    public boolean isAdmin() {
        for (Ruolo ruoloItem : ruoli) {
            if (ruoloItem.getCodice().equals(Ruolo.ROLE_ADMIN))
                return true;
        }
        return false;
    }

    public boolean isAttivo() {
        return this.stato != null && this.stato.equals(StatoUtente.ATTIVO);
    }

    public boolean isDisabilitato() {
        return this.stato != null && this.stato.equals(StatoUtente.DISABILITATO);
    }

}
