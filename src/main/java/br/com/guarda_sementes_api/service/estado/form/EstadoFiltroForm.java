package br.com.guarda_sementes_api.service.estado.form;

public record EstadoFiltroForm(
        Long estNrId,
        String estTxSigla,
        String estTxNome
) {
}
