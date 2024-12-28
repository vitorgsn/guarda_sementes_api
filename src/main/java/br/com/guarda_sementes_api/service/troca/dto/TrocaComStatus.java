package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TrocaComStatus {
    UUID getTroNrId();
    String getTroTxInstruncoes();
    UUID getUsuNrIdRemetente();
    UUID getUsuNrIdDestinatario();
    LocalDateTime getTroDtCreatedAt();
    @Enumerated(EnumType.STRING)
    StatusTrocaEnum getSttTxStatus();
    LocalDateTime getSttDtStatusTroca();
}
