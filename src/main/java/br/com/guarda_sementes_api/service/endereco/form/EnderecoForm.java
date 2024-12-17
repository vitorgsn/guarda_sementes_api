package br.com.guarda_sementes_api.service.endereco.form;

public record EnderecoForm(
        String endTxBairro,
        String endTxLogradouro,
        String endTxNumero,
        String endTxReferencia,
        Long cidNrId
) {
}
