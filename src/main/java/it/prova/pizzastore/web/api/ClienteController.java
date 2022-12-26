package it.prova.pizzastore.web.api;

import it.prova.pizzastore.dto.ClienteDTO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.ClienteService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDTO> getAll() {
        return ClienteDTO.createClienteDTOListFromModelList(clienteService.listAllClienti());
    }

    @PostMapping
    public ClienteDTO createNew(@Valid @RequestBody ClienteDTO clienteInput) {
        // se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
        // non sta bene
        if (clienteInput.getId() != null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Cliente clienteInserito = clienteService.inserisciNuovo(clienteInput.buildClienteModel());
        return ClienteDTO.buildClienteDTOFromModel(clienteInserito);
    }

    @GetMapping("/{id}")
    public ClienteDTO findById(@PathVariable(value = "id", required = true) long id) {
        Cliente cliente = clienteService.caricaSingoloCliente(id);

        if (cliente == null)
            throw new NotFoundException("Cliente not found con id: " + id);

        return ClienteDTO.buildClienteDTOFromModel(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id", required = true) long id) {
        Cliente cliente = clienteService.caricaSingoloCliente(id);

        if (cliente == null)
            throw new NotFoundException("Cliente not found con id: " + id);

        clienteService.rimuovi(id);
    }

    @PutMapping("/{id}")
    public ClienteDTO update(@Valid @RequestBody ClienteDTO clienteInput, @PathVariable(required = true) Long id) {
        Cliente cliente = clienteService.caricaSingoloCliente(id);

        if (cliente == null)
            throw new NotFoundException("Cliente not found con id: " + id);

        clienteInput.setId(id);
        Cliente clienteAggiornato = clienteService.aggiorna(clienteInput.buildClienteModel());
        return ClienteDTO.buildClienteDTOFromModel(clienteAggiornato);
    }

    @PostMapping("/search")
    public List<ClienteDTO> search(@RequestBody ClienteDTO example) {
        return ClienteDTO.createClienteDTOListFromModelList(clienteService.findByExample(example.buildClienteModel()));
    }
}
