package it.prova.pizzastore.web.api;

import it.prova.pizzastore.dto.ClienteDTO;
import it.prova.pizzastore.dto.DateDTO;
import it.prova.pizzastore.dto.OrdineDTO;
import it.prova.pizzastore.dto.StatisticheDTO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.ClienteService;
import it.prova.pizzastore.service.OrdineService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<OrdineDTO> getAll() {
        return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAllOrdini());
    }

    @PostMapping
    public OrdineDTO createNew(@Valid @RequestBody OrdineDTO ordineInput){

        if(ordineInput.getId()!=null)
            throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

        Ordine ordineInserito = ordineService.inserisciNuovo(ordineInput.buildOrdineModel());
        return  OrdineDTO.buildOrdineDTOFromModel(ordineInserito, false, false, false);

    }

    @GetMapping("/{id}")
    public OrdineDTO findById(@PathVariable(value = "id", required = true) long id){
        Ordine ordine = ordineService.caricaSingoloOrdine(id);
        if(ordine == null) throw new NotFoundException("Ordine not found con id: " +id);
        return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
    }

    @PutMapping("/{id}")
    public OrdineDTO update(@Valid @RequestBody OrdineDTO ordineInput, @PathVariable(value = "id", required = true) long id){
        Ordine ordine = ordineService.caricaSingoloOrdine(id);
        if(ordine == null) throw new NotFoundException("Ordine not found con id: " +id);
        ordineInput.setId(id);
        Ordine ordineAggiornato = ordineService.aggiorna(ordineInput.buildOrdineModel());
        return OrdineDTO.buildOrdineDTOFromModel(ordine, false, false, false);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id", required = true) long id) {
        Ordine ordine = ordineService.caricaSingoloOrdine(id);
        if (ordine == null)
            throw new NotFoundException("Pizza not found con id: " + id);
        ordineService.rimuovi(id);
    }

    @PostMapping("/search")
    public List<OrdineDTO> search(@RequestBody OrdineDTO example) {
        return OrdineDTO.createOrdineDTOListFromModelList(ordineService.findByExample(example.buildOrdineModel()));
    }

    @PostMapping("/statistiche")
    public StatisticheDTO infos(@Valid @RequestBody DateDTO date){
        StatisticheDTO result = StatisticheDTO.builder().build();
        result.setCosti(ordineService.costiTotali(date.getDataInizio(), date.getDataFine()));
        result.setRicavi(ordineService.ricaviTotali(date.getDataInizio(), date.getDataFine()));
        result.setNumeroPizze(ordineService.pizzeTotali(date.getDataInizio(), date.getDataFine()));
        result.setNumeroOrdini(ordineService.ordiniTotali(date.getDataInizio(), date.getDataFine()));
        List<Cliente> clientiVirtuosi = ordineService.findClientiVirtuosi(date.getDataInizio(), date.getDataFine());
        result.setClientiVirtuosi(ClienteDTO.createClienteDTOSetFromModelList(clientiVirtuosi));
        return result;

    }


}
