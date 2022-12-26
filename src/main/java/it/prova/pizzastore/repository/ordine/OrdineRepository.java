package it.prova.pizzastore.repository.ordine;

import it.prova.pizzastore.model.Ordine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrdineRepository extends CrudRepository<Ordine, Long>, CustomOrdineRepository {
    @Query("from Ordine o left join fetch o.pizze left join fetch o.fattorino left join fetch o.cliente where o.id = ?1 ")
    Optional<Ordine> findByIdEager(Long id);
}
