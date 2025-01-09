package br.com.guarda_sementes_api.service.sementedisponiveltroca.dto;

import br.com.guarda_sementes_api.model.sementedisponiveltroca.SementeDisponivelTrocaEntidade;

import java.time.LocalDateTime;

public record SementeDisponivelTrocaDto(
        Long sdtNrId,
        Boolean sdtBlDisponivel,
        Float sdtNrQuantidade,
        Long semNrIdSemente,
        String sdtTxObservacoes,
        LocalDateTime sdtDtCreatedAt,
        LocalDateTime sdtDtUpdatedAt
) {
    public SementeDisponivelTrocaDto(SementeDisponivelTrocaEntidade entidade) {
        this(
                entidade.getSdtNrId(),
                entidade.getSdtBlDisponivel(),
                entidade.getSdtNrQuantidade(),
                entidade.getSemNrIdSemente(),
                entidade.getSdtTxObservacoes(),
                entidade.getSdtDtCreatedAt(),
                entidade.getSdtDtUpdatedAt()
        );
    }
}
