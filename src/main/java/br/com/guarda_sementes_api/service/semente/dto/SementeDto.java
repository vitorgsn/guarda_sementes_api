package br.com.guarda_sementes_api.service.semente.dto;

import br.com.guarda_sementes_api.model.semente.SementeEntidade;

import java.time.LocalDateTime;

public record SementeDto(
        Long semNrId,
        String semTxNome,
        Float semNrQuantidade,
        String semTxDescricao,
        LocalDateTime semDtCreatedAt,
        LocalDateTime semDtUpdateAt,
        Boolean semBlAtivo,
        Long armNrId
) {
    public SementeDto(SementeEntidade entidade) {
        this(
                entidade.getSemNrId(),
                entidade.getSemTxNome(),
                entidade.getSemNrQuantidade(),
                entidade.getSemTxDescricao(),
                entidade.getSemDtCreatedAt(),
                entidade.getSemDtUpdateAt(),
                entidade.getSemBlAtivo(),
                entidade.getArmNrId()
        );
    }
}
