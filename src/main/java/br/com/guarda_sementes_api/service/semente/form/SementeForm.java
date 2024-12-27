package br.com.guarda_sementes_api.service.semente.form;

public record SementeForm(
        String semTxNome,
        Float semNrQuantidade,
        String semTxDescricao,
        Long armNrId
) {
}
