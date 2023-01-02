package it.prova.pizzastore.repository.ordine;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {
    @Query("from Ordine o left join fetch o.pizze left join fetch o.fattorino left join fetch o.cliente where o.id = ?1 ")
    Optional<Ordine> findByIdEager(Long id);

    @Query("SELECT sum(o.costo) from Ordine o where o.data between ?1 and ?2 ")
    Integer sumRicaviTotali(LocalDate dataInizio, LocalDate dataFine);
    @Query("SELECT count(o.id) from Ordine o where o.data between ?1 and ?2 ")
    Integer countOrdiniTotali(LocalDate dataInizio, LocalDate dataFine);
    @Query("SELECT sum(o.pizze.size) from Ordine o where o.data between ?1 and ?2 ")
    Integer countPizzeTotali(LocalDate dataInizio, LocalDate dataFine);
    @Query("SELECT distinct o.cliente from Ordine o where o.costo >= 100 and o.data between ?1 and ?2 ")
    List<Cliente> findClientiVirtuosi(LocalDate dataInizio, LocalDate dataFine);
}
