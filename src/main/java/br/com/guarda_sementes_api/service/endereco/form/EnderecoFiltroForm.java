package br.com.guarda_sementes_api.service.endereco.form;

public record EnderecoFiltroForm(
        Long endNrId,
        String endTxBairro,
        String endTxLogradouro,
        String endTxNumero,
        String endTxReferencia,
        Long cidNrId,
        String cidTxNome,
        Long estNrId,
        String estTxNome,
        String estTxSigla,
        Boolean endBlEnderecoPadrao
) {
}
