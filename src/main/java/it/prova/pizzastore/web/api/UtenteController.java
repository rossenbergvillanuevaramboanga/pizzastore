package it.prova.pizzastore.web.api;

public class UtenteController {

    //TODO Implement UtenteController
/*    @Autowired
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
    }*/
}
