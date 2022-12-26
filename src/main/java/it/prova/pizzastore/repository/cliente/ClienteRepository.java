package it.prova.pizzastore.repository.cliente;

import it.prova.pizzastore.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Long>, CustomClienteRepository{
    Cliente findByNomeAndCognome(String nome, String cognome);
}
