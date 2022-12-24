package it.prova.pizzastore.repository.ordine;

import it.prova.pizzastore.model.Ordine;
import org.springframework.data.repository.CrudRepository;

public interface OrdineRepository extends CrudRepository<Ordine, Long> {
}
