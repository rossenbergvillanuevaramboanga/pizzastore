package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repository;
    @Override
    public List<Cliente> listAllClienti() {
        return (List<Cliente>) repository.findAll();
    }

    @Override
    public Cliente caricaSingoloCliente(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public Cliente aggiorna(Cliente clienteInstance) {
        Cliente clienteReloaded = repository.findById(clienteInstance.getId()).orElse(null);
        if(clienteReloaded == null )
            throw new RuntimeException("Elemento non trovato");
        clienteReloaded.setNome(clienteInstance.getNome());
        clienteReloaded.setCognome(clienteInstance.getCognome());
        clienteReloaded.setIndirizzo(clienteInstance.getIndirizzo());
        clienteReloaded.setAttivo(clienteInstance.isAttivo());
        return repository.save(clienteInstance);

    }

    @Transactional
    public Cliente inserisciNuovo(Cliente clienteInstance) {
        clienteInstance.setAttivo(true);
        return repository.save(clienteInstance);
    }

    @Transactional
    public void rimuovi(Long idToRemove) {
        Cliente clienteReloaded = repository.findById(idToRemove).orElse(null);
        if(clienteReloaded == null)
            throw new RuntimeException("Elemento non trovato");
        clienteReloaded.setAttivo(false);
        repository.save(clienteReloaded);
    }
}
