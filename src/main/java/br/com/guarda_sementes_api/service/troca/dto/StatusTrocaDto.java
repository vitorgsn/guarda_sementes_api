package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.StatusTrocaEntidade;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

public record StatusTrocaDto(
        Long sttNrId,
        @Enumerated(EnumType.STRING)
        StatusTrocaEnum sttTxStatus,
        UUID troNrIdTroca,
        LocalDateTime sttDtStatusTroca
) {
    public StatusTrocaDto(StatusTrocaEntidade entidade) {
        this(
                entidade.getSttNrId(),
                entidade.getSttTxStatus(),
                entidade.getTroNrIdTroca(),
                entidade.getSttDtStatusTroca()
        );
    }
}
