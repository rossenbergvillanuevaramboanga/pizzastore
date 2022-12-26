package it.prova.pizzastore.web.api;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.security.dto.UtenteInfoJWTResponseDTO;
import it.prova.pizzastore.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {


    @Autowired
    private UtenteService utenteService;

    // questa mi serve solo per capire se solo ADMIN vi ha accesso
    @GetMapping("/testSoloAdmin")
    public String test() {
        return "OK";
    }

    @GetMapping(value = "/userInfo")
    public ResponseEntity<UtenteInfoJWTResponseDTO> getUserInfo() {

        // se sono qui significa che sono autenticato quindi devo estrarre le info dal
        // contesto
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // estraggo le info dal principal
        Utente utenteLoggato = utenteService.findByUsername(username);
        List<String> ruoli = utenteLoggato.getRuoli().stream().map(item -> item.getCodice())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new UtenteInfoJWTResponseDTO(utenteLoggato.getNome(), utenteLoggato.getCognome(),
                utenteLoggato.getUsername(), utenteLoggato.getEmail(), ruoli));
    }
}
