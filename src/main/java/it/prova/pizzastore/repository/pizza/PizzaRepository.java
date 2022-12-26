package it.prova.pizzastore.repository.pizza;

import it.prova.pizzastore.model.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long>, CustomPizzaRepository{
}
