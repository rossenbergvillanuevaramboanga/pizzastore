package it.prova.pizzastore.web.api.exception;

public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(String message) {
        super(message);
    }
}
