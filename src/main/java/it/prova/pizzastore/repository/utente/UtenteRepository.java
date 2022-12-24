package it.prova.pizzastore.repository.utente;

import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtenteRepository extends CrudRepository<Utente,Long> {

    @EntityGraph(attributePaths = { "ruoli" })
    Optional<Utente> findByUsername(String username);

    @Query("from Utente u left join fetch u.ruoli where u.id = ?1")
    Optional<Utente> findByIdConRuoli(Long id);

    Utente findByUsernameAndPassword(String username, String password);

    //caricamento eager, ovviamente si può fare anche con jpql
    @EntityGraph(attributePaths = { "ruoli" })
    Utente findByUsernameAndPasswordAndStato(String username,String password, StatoUtente stato);
}
