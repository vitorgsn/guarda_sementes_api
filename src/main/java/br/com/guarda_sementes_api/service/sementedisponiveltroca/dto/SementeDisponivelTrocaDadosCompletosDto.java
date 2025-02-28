package br.com.guarda_sementes_api.service.sementedisponiveltroca.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SementeDisponivelTrocaDadosCompletosDto(
        Long sdtNrId,
        Boolean sdtBlDisponivel,
        Float sdtNrQuantidade,
        String sdtTxObservacoes,
        Long semNrIdSemente,
        String semTxNome,
        UUID usuNrId,
        Long cidNrId,
        String cidTxNome,
        Long estNrId,
        String estTxNome,
        String estTxSigla,
        LocalDateTime sdtDtCreatedAt,
        LocalDateTime sdtDtUpdatedAt
) {
    public SementeDisponivelTrocaDadosCompletosDto (SementeDisponivelTrocaDadosCompletos semente) {
        this (
                semente.getSdtNrId(),
                semente.getSdtBlDisponivel(),
                semente.getSdtNrQuantidade(),
                semente.getSdtTxObservacoes(),
                semente.getSemNrIdSemente(),
                semente.getSemTxNome(),
                semente.getUsuNrId(),
                semente.getCidNrId(),
                semente.getCidTxNome(),
                semente.getEstNrId(),
                semente.getEstTxNome(),
                semente.getEstTxSigla(),
                semente.getSdtDtCreatedAt(),
                semente.getSdtDtUpdatedAt()
        );
    }
}
