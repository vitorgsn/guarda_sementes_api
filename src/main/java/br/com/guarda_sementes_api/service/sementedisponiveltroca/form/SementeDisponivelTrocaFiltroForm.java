package br.com.guarda_sementes_api.service.sementedisponiveltroca.form;

public record SementeDisponivelTrocaFiltroForm(
        Long sdtNrId,
        Float sdtNrQuantidade,
        Long semNrIdSemente,
        String sdtTxObservacoes
) {
}
