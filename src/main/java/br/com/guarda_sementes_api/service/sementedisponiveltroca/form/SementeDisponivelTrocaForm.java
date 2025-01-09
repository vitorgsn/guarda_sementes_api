package br.com.guarda_sementes_api.service.sementedisponiveltroca.form;

public record SementeDisponivelTrocaForm(
        Float sdtNrQuantidade,
        Long semNrIdSemente,
        String sdtTxObservacoes
) {
}
