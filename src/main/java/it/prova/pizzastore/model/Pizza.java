package it.prova.pizzastore.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor

@Entity
@Table(name="pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "ingredienti")
    private String ingredienti;
    @Column(name = "prezzoBase")
    private Integer prezzoBase;
    @Column(name = "attivo")
    private Boolean attivo;

}
