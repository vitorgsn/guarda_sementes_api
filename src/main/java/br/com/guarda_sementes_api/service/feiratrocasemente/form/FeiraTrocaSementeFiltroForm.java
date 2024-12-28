package br.com.guarda_sementes_api.service.feiratrocasemente.form;

public record FeiraTrocaSementeFiltroForm(
        Long ftsNrId,
        Float ftsNrQuantidade,
        Long semNrIdSemente
) {
}
