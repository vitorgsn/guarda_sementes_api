package br.com.guarda_sementes_api.service.cidade.dto;

public record CidadeDadosCompletosDto(
        Long cidNrId,
        String cidTxNome,
        Long estNrId,
        String estTxNome,
        String estTxSigla
) {
    public CidadeDadosCompletosDto (CidadeDadosCompletos cidade) {
        this (
                cidade.getCidNrId(),
                cidade.getCidTxNome(),
                cidade.getEstNrId(),
                cidade.getEstTxNome(),
                cidade.getEstTxSigla()
        );
    }
}
