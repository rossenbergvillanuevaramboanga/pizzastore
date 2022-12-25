package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Ordine;

import java.util.List;

public interface OrdineService {

    public List<Ordine> listAllOrdini();

    public Ordine caricaSingoloOrdine(Long id);

    public Ordine caricaSingoloOrdineEager(Long id);

    public void aggiorna(Ordine ordineInstance);

    public void inserisciNuovo(Ordine ordineInstance);

    public void rimuovi(Long idToRemove);
}
