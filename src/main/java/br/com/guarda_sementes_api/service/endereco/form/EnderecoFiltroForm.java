package br.com.guarda_sementes_api.service.endereco.form;

public record EnderecoFiltroForm(
        String endTxBairro,
        String endTxLogradouro,
        String endTxNumero,
        String endTxReferencia,
        Long cidNrId
) {
}
