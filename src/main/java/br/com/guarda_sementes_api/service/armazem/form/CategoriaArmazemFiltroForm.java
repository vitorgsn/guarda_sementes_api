package br.com.guarda_sementes_api.service.armazem.form;

public record CategoriaArmazemFiltroForm(
        Long ctaNrId,
        String ctaTxNome,
        String ctaTxDescricao
) {
}
