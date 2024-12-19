package br.com.guarda_sementes_api.service.armazem.dto;

import br.com.guarda_sementes_api.model.armazem.CategoriaArmazemEntidade;

import java.time.LocalDateTime;

public record CategoriaArmazemDto(
        Long ctaNrId,
        String ctaTxNome,
        String ctaTxDescricao,
        LocalDateTime ctaDtCreatedAt,
        LocalDateTime ctaDtUpdateAt,
        Boolean ctaBlAtivo
) {
    public CategoriaArmazemDto(CategoriaArmazemEntidade entidade) {
        this(
                entidade.getCtaNrId(),
                entidade.getCtaTxNome(),
                entidade.getCtaTxDescricao(),
                entidade.getCtaDtCreatedAt(),
                entidade.getCtaDtUpdateAt(),
                entidade.getCtaBlAtivo()
        );
    }
}
