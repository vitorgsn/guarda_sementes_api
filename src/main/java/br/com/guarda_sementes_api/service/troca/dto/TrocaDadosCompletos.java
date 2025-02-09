package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TrocaDadosCompletos {
    UUID getTroNrId();
    String getTroTxInstrucoes();
    @Enumerated(EnumType.STRING)
    StatusTrocaEnum getSttTxStatus();
    LocalDateTime getSttDtStatusTroca();
    LocalDateTime getTroDtCreatedAt();
    UUID getUsuNrIdRemetente();
    String getUsuTxNomeRemetente();
    Long getSemNrIdSementeRemetente();
    String getSemTxNomeRemetente();
    Float getTroNrQuantidadeSementeRemetente();
    UUID getUsuNrIdDestinatario();
    String getUsuTxNomeDestinatario();
    Long getSemNrIdSementeDestinatario();
    String getSemTxNomeDestinatario();
    Float getTroNrQuantidadeSementeDestinatario();
}
