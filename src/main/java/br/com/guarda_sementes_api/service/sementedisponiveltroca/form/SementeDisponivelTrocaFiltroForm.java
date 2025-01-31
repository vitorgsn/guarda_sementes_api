package br.com.guarda_sementes_api.service.sementedisponiveltroca.form;

public record SementeDisponivelTrocaFiltroForm(
        Long sdtNrId,
        Float sdtNrQuantidade,
        String sdtTxObservacoes,
        Long semNrIdSemente,
        String semTxNome,
        Long cidNrId,
        String cidTxNome,
        Long estNrId,
        String estTxNome,
        String estTxSigla
) {
}
