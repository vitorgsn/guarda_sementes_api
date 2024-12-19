package br.com.guarda_sementes_api.service.armazem.dto;

import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;

import java.time.LocalDateTime;

public record ArmazemDto(
        Long armNrId,
        String armTxDescricao,
        LocalDateTime armDtCreatedAt,
        LocalDateTime armDtUpdateAt,
        Long ctaNrId,
        Boolean armBlAtivo
) {
    public ArmazemDto(ArmazemEntidade entidade) {
        this(
                entidade.getArmNrId(),
                entidade.getArmTxDescricao(),
                entidade.getArmDtCreatedAt(),
                entidade.getArmDtUpdateAt(),
                entidade.getCtaNrId(),
                entidade.getArmBlAtivo()
        );
    }
}
