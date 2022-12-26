package it.prova.pizzastore.repository.pizza;

import it.prova.pizzastore.model.Pizza;

import java.util.List;

public interface CustomPizzaRepository {

    List<Pizza> findByExample(Pizza example);
}
