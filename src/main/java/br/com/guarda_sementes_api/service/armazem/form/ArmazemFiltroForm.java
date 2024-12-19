package br.com.guarda_sementes_api.service.armazem.form;

public record ArmazemFiltroForm(
        Long armNrId,
        String armTxDescricao,
        Long ctaNrId
) {
}
