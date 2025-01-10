package br.com.guarda_sementes_api.exceptions;

public class RegistroJaExisteException extends RuntimeException {

    public RegistroJaExisteException(String message) {
        super(message);
    }
}
