package br.com.guarda_sementes_api.service.cidade.form;

public record CidadeFiltroForm(
        Long cidNrId,
        String cidTxNome,
        Long estNrId
) {
}
