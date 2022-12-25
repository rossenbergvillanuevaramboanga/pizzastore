package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Pizza;

import java.util.List;

public interface PizzaService {

    public List<Pizza> listAllPizzas();

    public Pizza caricaSingolaPizza(Long id);

    public void aggiorna(Pizza pizzaInstance);

    public void inserisciNuovo(Pizza pizzaInstance);

    public void rimuovi(Long idToRemove);
}
