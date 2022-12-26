package it.prova.pizzastore.web.api;

import it.prova.pizzastore.dto.PizzaDTO;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.PizzaService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {



    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<PizzaDTO> getAll() {
        return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.listAllPizzas());
    }

    @PostMapping
    public PizzaDTO createNew(@Valid @RequestBody PizzaDTO pizzaInput) {

        if (pizzaInput.getId() != null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Pizza pizzaInserita = pizzaService.inserisciNuovo(pizzaInput.buildPizzaModel());
        return PizzaDTO.buildPizzaDTOFromModel(pizzaInserita);
    }

    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable(value = "id", required = true) long id) {
        Pizza pizza = pizzaService.caricaSingolaPizza(id);

        if (pizza == null)
            throw new NotFoundException("Pizza not found con id: " + id);

        return PizzaDTO.buildPizzaDTOFromModel(pizza);
    }

    @PutMapping("/{id}")
    public PizzaDTO update(@Valid @RequestBody PizzaDTO pizzaInput, @PathVariable(required = true) Long id) {
        Pizza pizza = pizzaService.caricaSingolaPizza(id);

        if (pizza == null)
            throw new NotFoundException("Pizza not found con id: " + id);

        pizzaInput.setId(id);
        Pizza pizzaAggiornata = pizzaService.aggiorna(pizzaInput.buildPizzaModel());
        return PizzaDTO.buildPizzaDTOFromModel(pizzaAggiornata);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id", required = true) long id) {
        Pizza pizza = pizzaService.caricaSingolaPizza(id);
        if (pizza == null)
            throw new NotFoundException("Pizza not found con id: " + id);
        pizzaService.rimuovi(id);
    }

    @PostMapping("/search")
    public List<PizzaDTO> search(@RequestBody PizzaDTO example) {
        return PizzaDTO.createPizzaDTOListFromModelList(pizzaService.findByExample(example.buildPizzaModel()));
    }



}
