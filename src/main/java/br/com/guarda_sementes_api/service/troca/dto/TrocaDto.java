package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.TrocaEntidade;

import java.time.LocalDateTime;
import java.util.UUID;

public record TrocaDto(
        UUID troNrId,
        String troTxInstruncoes,
        UUID usuNrIdRemetente,
        UUID usuNrIdDestinatario,
        LocalDateTime troDtCreatedAt,
        LocalDateTime troDtUpdateAt
) {
    public TrocaDto (TrocaEntidade entidade) {
        this(
                entidade.getTroNrId(),
                entidade.getTroTxInstruncoes(),
                entidade.getUsuNrIdRemetente(),
                entidade.getUsuNrIdDestinatario(),
                entidade.getTroDtCreatedAt(),
                entidade.getTroDtUpdateAt()
        );
    }
}
