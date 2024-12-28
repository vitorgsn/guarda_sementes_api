package br.com.guarda_sementes_api.service.feiratrocasemente.dto;

import br.com.guarda_sementes_api.model.feiratrocasemente.FeiraTrocaSementeEntidade;

import java.time.LocalDateTime;

public record FeiraTrocaSementeDto(
        Long ftsNrId,
        Boolean ftsBlDisponivel,
        Float ftsNrQuantidade,
        Long semNrIdSemente,
        LocalDateTime ftsDtCreatedAt,
        LocalDateTime ftsDtUpdatedAt
) {
    public FeiraTrocaSementeDto (FeiraTrocaSementeEntidade entidade) {
        this(
                entidade.getFtsNrId(),
                entidade.getFtsBlDisponivel(),
                entidade.getFtsNrQuantidade(),
                entidade.getSemNrIdSemente(),
                entidade.getFtsDtCreatedAt(),
                entidade.getFtsDtUpdatedAt()
        );
    }
}
