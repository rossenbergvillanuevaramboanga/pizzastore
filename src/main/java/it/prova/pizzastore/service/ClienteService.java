package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Cliente;

import java.util.List;

public interface ClienteService {

    public List<Cliente> listAllClienti();

    public Cliente caricaSingoloCliente(Long id);

    public void aggiorna(Cliente clienteInstance);

    public void inserisciNuovo(Cliente clienteInstance);

    public void rimuovi(Long idToRemove);
}
