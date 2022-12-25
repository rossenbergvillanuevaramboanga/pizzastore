package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Ruolo;

import java.util.List;

public interface RuoloService {

    public List<Ruolo> listAll();

    public Ruolo caricaSingoloElemento(Long id);

    public void aggiorna(Ruolo ruoloInstance);

    public void inserisciNuovo(Ruolo ruoloInstance);

    public void rimuovi(Long idToRemove);

    public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice);
}
