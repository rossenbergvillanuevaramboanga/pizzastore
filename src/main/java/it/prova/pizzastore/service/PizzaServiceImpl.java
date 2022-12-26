package it.prova.pizzastore.service;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.pizza.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository repository;

    @Override
    public List<Pizza> listAllPizzas() {
        return (List<Pizza>) repository.findAll();
    }

    @Override
    public Pizza caricaSingolaPizza(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void aggiorna(Pizza pizzaInstance) {
        Pizza pizzaReloaded = repository.findById(pizzaInstance.getId()).orElse(null);
        if(pizzaReloaded == null) throw new RuntimeException("Elemento non trovato");
        pizzaReloaded.setDescrizione(pizzaInstance.getDescrizione());
        pizzaReloaded.setIngredienti(pizzaInstance.getIngredienti());
        pizzaReloaded.setPrezzoBase(pizzaInstance.getPrezzoBase());
        pizzaReloaded.setAttivo(pizzaInstance.getAttivo());
        repository.save(pizzaReloaded);
    }

    @Transactional
    public void inserisciNuovo(Pizza pizzaInstance) {
        pizzaInstance.setAttivo(true);
        repository.save(pizzaInstance);
    }

    @Transactional
    public void rimuovi(Long idToRemove) {
        repository.deleteById(idToRemove);
    }

}
