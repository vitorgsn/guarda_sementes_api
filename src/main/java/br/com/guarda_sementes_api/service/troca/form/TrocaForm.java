package br.com.guarda_sementes_api.service.troca.form;

import java.util.UUID;

public record TrocaForm(
        String troTxInstruncoes,
        UUID usuNrIdDestinatario,
        //semente escolhida da lista de disponíveis para troca
        SementeTrocaForm ofertadaNasDisponiveis,
        //semente escolhida pelo usuário para trocar pela disponível
        SementeTrocaForm ofertadaParaTroca
) {
}
