package br.com.guarda_sementes_api.service.troca.form;

import java.util.List;
import java.util.UUID;

public record TrocaForm(
        String troTxInstruncoes,
        UUID usuNrIdDestinatario,
        List<SementeTrocaForm> sementesTroca
) {
}
