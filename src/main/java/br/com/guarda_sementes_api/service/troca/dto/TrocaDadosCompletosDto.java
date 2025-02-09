package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrocaDadosCompletosDto(
        UUID troNrId,
        String troTxInstrucoes,
        @Enumerated(EnumType.STRING)
        StatusTrocaEnum sttTxStatus,
        LocalDateTime sttDtStatusTroca,
        LocalDateTime troDtCreatedAt,
        UUID usuNrIdRemetente,
        String usuTxNomeRemetente,
        Long semNrIdSementeRemetente,
        String semTxNomeRemetente,
        Float troNrQuantidadeSementeRemetente,
        UUID usuNrIdDestinatario,
        String usuTxNomeDestinatario,
        Long semNrIdSementeDestinatario,
        String semTxNomeDestinatario,
        Float troNrQuantidadeSementeDestinatario
) {
    public TrocaDadosCompletosDto(TrocaDadosCompletos troca) {
        this(
                troca.getTroNrId(),
                troca.getTroTxInstrucoes(),
                troca.getSttTxStatus(),
                troca.getSttDtStatusTroca(),
                troca.getTroDtCreatedAt(),
                troca.getUsuNrIdRemetente(),
                troca.getUsuTxNomeRemetente(),
                troca.getSemNrIdSementeRemetente(),
                troca.getSemTxNomeRemetente(),
                troca.getTroNrQuantidadeSementeRemetente(),
                troca.getUsuNrIdDestinatario(),
                troca.getUsuTxNomeDestinatario(),
                troca.getSemNrIdSementeDestinatario(),
                troca.getSemTxNomeDestinatario(),
                troca.getTroNrQuantidadeSementeDestinatario()
        );
    }
}
