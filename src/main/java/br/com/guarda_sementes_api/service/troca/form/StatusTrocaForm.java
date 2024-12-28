package br.com.guarda_sementes_api.service.troca.form;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

public record StatusTrocaForm(
        @Enumerated(EnumType.STRING)
        StatusTrocaEnum sttTxStatus,
        UUID troNrIdTroca
) {
}
