package br.com.guarda_sementes_api.service.sementedisponiveltroca.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SementeDisponivelTrocaDadosCompletos {
    Long getSdtNrId();
    Boolean getSdtBlDisponivel();
    Float getSdtNrQuantidade();
    String getSdtTxObservacoes();
    Long getSemNrIdSemente();
    String getSemTxNome();
    UUID getUsuNrId();
    Long getCidNrId();
    String getCidTxNome();
    Long getEstNrId();
    String getEstTxNome();
    String getEstTxSigla();
    LocalDateTime getSdtDtCreatedAt();
    LocalDateTime getSdtDtUpdatedAt();
}
