package br.com.guarda_sementes_api.service.cidade.dto;

import br.com.guarda_sementes_api.model.cidade.CidadeEntidade;

import java.time.LocalDateTime;

public record CidadeDto(
        Long cidNrId,
        String cidTxNome,
        LocalDateTime cidDtCreatedAt,
        LocalDateTime cidDtUpdateAt,
        Long estNrId,
        Boolean cidBlAtivo
) {
    public CidadeDto(CidadeEntidade entidade) {
        this(
                entidade.getCidNrId(),
                entidade.getCidTxNome(),
                entidade.getCidDtCreatedAt(),
                entidade.getCidDtUpdateAt(),
                entidade.getEstNrId(),
                entidade.getCidBlAtivo()
        );
    }
}
