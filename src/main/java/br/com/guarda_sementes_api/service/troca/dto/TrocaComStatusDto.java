package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrocaComStatusDto(
        UUID troNrId,
        String troTxInstruncoes,
        UUID usuNrIdRemetente,
        UUID usuNrIdDestinatario,
        LocalDateTime troDtCreatedAt,
        @Enumerated(EnumType.STRING)
        StatusTrocaEnum sttTxStatus,
        LocalDateTime sttDtStatusTroca
) {
    public TrocaComStatusDto(TrocaComStatus troca) {
        this(
                troca.getTroNrId(),
                troca.getTroTxInstruncoes(),
                troca.getUsuNrIdRemetente(),
                troca.getUsuNrIdDestinatario(),
                troca.getTroDtCreatedAt(),
                troca.getSttTxStatus(),
                troca.getSttDtStatusTroca()
        );
    }
}
