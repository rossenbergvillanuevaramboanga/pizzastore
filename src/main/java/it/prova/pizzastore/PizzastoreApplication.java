package it.prova.pizzastore;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.HashSet;

@SpringBootApplication
public class PizzastoreApplication implements CommandLineRunner {

    @Autowired
    private RuoloService ruoloServiceInstance;
    @Autowired
    private UtenteService utenteServiceInstance;
    @Autowired
    private ClienteService clienteServiceInstance;
    @Autowired
    private OrdineService ordineServiceInstance;
    @Autowired
    private PizzaService pizzaServiceInstance;

    public static void main(String[] args) {
        SpringApplication.run(PizzastoreApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*Creazione dei Ruoli degli Utenti*/
        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
            ruoloServiceInstance.inserisciNuovo(Ruolo.builder().descrizione("Administrator").codice(Ruolo.ROLE_ADMIN).build());
        }

        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO) == null) {
            ruoloServiceInstance.inserisciNuovo(Ruolo.builder().descrizione("Pizzaiolo").codice(Ruolo.ROLE_PIZZAIOLO).build());
        }

        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO) == null) {
            ruoloServiceInstance.inserisciNuovo(Ruolo.builder().descrizione("Proprietario").codice(Ruolo.ROLE_PROPRIETARIO).build());
        }

        if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO) == null) {
            ruoloServiceInstance.inserisciNuovo(Ruolo.builder().descrizione("Fattorino").codice(Ruolo.ROLE_FATTORINO).build());
        }

        /*Creazione degli Utenti*/
        if (utenteServiceInstance.findByUsername("admin123_?") == null) {
            Utente admin = Utente.builder()
                    .username("admin123_?")
                    .password("admin123_?")
                    .nome("Admin")
                    .cognome("Admin")
                    .dateCreated(LocalDate.now())
                    .ruoli(new HashSet<>(0))
                    .build();
            admin.setEmail("a.admin@prova.it");
            admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
            utenteServiceInstance.inserisciNuovo(admin);
            // l'inserimento avviene come created ma io voglio attivarlo
            utenteServiceInstance.changeUserAbilitation(admin.getId());
        }

        if (utenteServiceInstance.findByUsername("pizzaiolo123_?") == null) {
            Utente pizzaiolo = Utente.builder()
                    .username("pizzaiolo123_?")
                    .password("pizzaiolo123_?")
                    .nome("Pizzaiolo")
                    .cognome("Pizzaiolo")
                    .dateCreated(LocalDate.now())
                    .ruoli(new HashSet<>(0))
                    .build();
            pizzaiolo.setEmail("p.pizzaiolo@prova.it");
            pizzaiolo.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo", Ruolo.ROLE_PIZZAIOLO));
            utenteServiceInstance.inserisciNuovo(pizzaiolo);
            // l'inserimento avviene come created ma io voglio attivarlo
            utenteServiceInstance.changeUserAbilitation(pizzaiolo.getId());
        }

        if (utenteServiceInstance.findByUsername("proprietario123_?") == null) {
            Utente proprietario = Utente.builder()
                    .username("proprietario123_?")
                    .password("proprietario123_?")
                    .nome("Proprietario")
                    .cognome("Proprietario")
                    .dateCreated(LocalDate.now())
                    .ruoli(new HashSet<>(0))
                    .build();
            proprietario.setEmail("p.proprietario@prova.it");
            proprietario.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Proprietario", Ruolo.ROLE_PROPRIETARIO));
            utenteServiceInstance.inserisciNuovo(proprietario);
            // l'inserimento avviene come created ma io voglio attivarlo
            utenteServiceInstance.changeUserAbilitation(proprietario.getId());
        }

        if (utenteServiceInstance.findByUsername("fattorino123_?") == null) {
            Utente fattorino = Utente.builder()
                    .username("fattorino123_?")
                    .password("fattorino123_?")
                    .nome("Fattorino")
                    .cognome("Fattorino")
                    .dateCreated(LocalDate.now())
                    .ruoli(new HashSet<>(0))
                    .build();
            fattorino.setEmail("f.fattorino@prova.it");
            fattorino.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino", Ruolo.ROLE_FATTORINO));
            utenteServiceInstance.inserisciNuovo(fattorino);
            // l'inserimento avviene come created ma io voglio attivarlo
            utenteServiceInstance.changeUserAbilitation(fattorino.getId());
        }

        /*Creazione Pizze*/
        if(pizzaServiceInstance.findByDescrizione("Margherita") == null){
            Pizza margherita = Pizza.builder()
                    .descrizione("Margherita")
                    .prezzoBase(6)
                    .ingredienti("Mozzerella, Pomodoro, Basilico")
                    .build();
            pizzaServiceInstance.inserisciNuovo(margherita);
        }

        if(pizzaServiceInstance.findByDescrizione("Boscaiola") == null){
            Pizza boscaiola = Pizza.builder()
                    .descrizione("Boscaiola")
                    .prezzoBase(8)
                    .ingredienti("Salsiccia, Mozzarella, Funghi")
                    .build();
            pizzaServiceInstance.inserisciNuovo(boscaiola);
        }

        if(pizzaServiceInstance.findByDescrizione("Capricciosa") == null){
            Pizza capricciosa = Pizza.builder()
                    .descrizione("Capricciosa")
                    .prezzoBase(8)
                    .ingredienti("Pomodoro, Mozzarella, Prosciutto, Olive, Funghi, Capperi e Carciofini")
                    .build();
            pizzaServiceInstance.inserisciNuovo(capricciosa);
        }

    }
}
