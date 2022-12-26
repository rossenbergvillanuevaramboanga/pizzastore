package it.prova.pizzastore.repository.ordine;

import it.prova.pizzastore.model.Ordine;

import java.util.List;

public interface CustomOrdineRepository {

    List<Ordine> findByExample(Ordine example);
}
