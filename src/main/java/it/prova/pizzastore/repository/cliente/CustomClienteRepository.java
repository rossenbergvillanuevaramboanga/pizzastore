package it.prova.pizzastore.repository.cliente;

import it.prova.pizzastore.model.Cliente;

import java.util.List;

public interface CustomClienteRepository {

    List<Cliente> findByExample(Cliente example);
}
