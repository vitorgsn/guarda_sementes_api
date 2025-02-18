package br.com.guarda_sementes_api.exceptions;

public class RestricaoPorRegraDeNegocioException extends RuntimeException {
    public RestricaoPorRegraDeNegocioException(String message) {
        super(message);
    }
}
