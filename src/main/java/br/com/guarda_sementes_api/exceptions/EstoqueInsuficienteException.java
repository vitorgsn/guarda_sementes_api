package br.com.guarda_sementes_api.exceptions;

public class EstoqueInsuficienteException extends RuntimeException{
    public EstoqueInsuficienteException(String message) {
        super(message);
    }
}
