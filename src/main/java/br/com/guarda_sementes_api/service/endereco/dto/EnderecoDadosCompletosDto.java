package br.com.guarda_sementes_api.service.endereco.dto;

public record EnderecoDadosCompletosDto(
        Long endNrId,
        String endTxBairro,
        String endTxLogradouro,
        String endTxNumero,
        String endTxReferencia,
        Boolean endBlEnderecoPadrao,
        Long cidNrId,
        String cidTxNome,
        Long estNrId,
        String estTxNome,
        String estTxSigla
) {
    public EnderecoDadosCompletosDto(EnderecoDadosCompletos endereco) {
        this (
                endereco.getEndNrId(),
                endereco.getEndTxBairro(),
                endereco.getEndTxLogradouro(),
                endereco.getEndTxNumero(),
                endereco.getEndTxReferencia(),
                endereco.getEndBlEnderecoPadrao(),
                endereco.getCidNrId(),
                endereco.getCidTxNome(),
                endereco.getEstNrId(),
                endereco.getEstTxNome(),
                endereco.getEstTxSigla()
        );
    }
}
