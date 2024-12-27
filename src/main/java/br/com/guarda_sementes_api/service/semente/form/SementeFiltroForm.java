package br.com.guarda_sementes_api.service.semente.form;

public record SementeFiltroForm(
        Long semNrId,
        String semTxNome,
        Float semNrQuantidade,
        String semTxDescricao,
        Long armNrId
) {
}
