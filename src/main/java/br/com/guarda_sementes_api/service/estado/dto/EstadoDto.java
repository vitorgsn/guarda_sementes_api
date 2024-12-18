package br.com.guarda_sementes_api.service.estado.dto;

import br.com.guarda_sementes_api.model.estado.EstadoEntidade;

import java.time.LocalDateTime;

public record EstadoDto(
        Long estNrId,
        String estTxSigla,
        String estTxNome,
        LocalDateTime estDtCreatedAt,
        LocalDateTime estDtUpdateAt,
        Boolean estBlAtivo
) {
    public EstadoDto (EstadoEntidade entidade) {
        this(
                entidade.getEstNrId(),
                entidade.getEstTxSigla(),
                entidade.getEstTxNome(),
                entidade.getEstDtCreatedAt(),
                entidade.getEstDtUpdateAt(),
                entidade.getEstBlAtivo()
        );
    }
}
