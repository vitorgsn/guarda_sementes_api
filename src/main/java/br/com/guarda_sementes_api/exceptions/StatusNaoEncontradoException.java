package br.com.guarda_sementes_api.exceptions;

public class StatusNaoEncontradoException extends RuntimeException{
    public StatusNaoEncontradoException(String message) {
        super(message);
    }
}
