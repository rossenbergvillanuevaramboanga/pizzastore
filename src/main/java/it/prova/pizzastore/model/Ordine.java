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
@Table(name="ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany
    @JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
    private Set<Pizza> pizze = new HashSet<Pizza>(0);

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "closed")
    private boolean closed;

    @Column(name = "codice")
    private String codice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utente_id", nullable = true)
    private Utente fattorino;

    @Column(name = "costo")
    private Integer costo;



}
