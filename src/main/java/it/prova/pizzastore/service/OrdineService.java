package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;

import java.time.LocalDate;
import java.util.List;

public interface OrdineService {

    public List<Ordine> listAllOrdini();

    public Ordine caricaSingoloOrdine(Long id);

    public Ordine caricaSingoloOrdineEager(Long id);

    public Ordine aggiorna(Ordine ordineInstance);

    public Ordine inserisciNuovo(Ordine ordineInstance);

    public void rimuovi(Long idToRemove);

    public List<Ordine> findByExample(Ordine example);

    public Integer ricaviTotali(LocalDate dataInizio, LocalDate dataFine);

    public Integer costiTotali(LocalDate dataInizio, LocalDate dataFine);

    public Integer ordiniTotali(LocalDate dataInizio, LocalDate dataFine);

    public Integer pizzeTotali(LocalDate dataInizio, LocalDate dataFine);

    public List<Cliente> findClientiVirtuosi(LocalDate dataInizio, LocalDate dataFine);
}
