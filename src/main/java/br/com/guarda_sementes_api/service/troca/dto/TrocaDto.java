package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.TrocaEntidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrocaDto(
        UUID troNrId,
        String troTxInstrucoes,
        LocalDateTime troDtCreatedAt,
        UUID usuNrIdRemetente,
        Long semNrIdSementeRemetente,
        Float troNrQuantidadeSementeRemetente,
        UUID usuNrIdDestinatario,
        Long semNrIdSementeDestinatario,
        Float troNrQuantidadeSementeDestinatario
) {
    public TrocaDto (TrocaEntidade entidade) {
        this(
                entidade.getTroNrId(),
                entidade.getTroTxInstruncoes(),
                entidade.getTroDtCreatedAt(),
                entidade.getUsuNrIdRemetente(),
                entidade.getSemNrIdSementeRemetente(),
                entidade.getTroNrQuantidadeSementeRemetente(),
                entidade.getUsuNrIdDestinatario(),
                entidade.getSemNrIdSementeDestinatario(),
                entidade.getTroNrQuantidadeSementeDestinatario()
        );
    }
}
