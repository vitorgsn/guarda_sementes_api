package br.com.guarda_sementes_api.service.troca.form;

import java.util.UUID;

public record TrocaForm(
        String troTxInstrucoes,
        //semente escolhida da lista de disponíveis para troca
        UUID usuNrIdDestinatario,
        Long semNrIdSementeDestinatario,
        Float troNrQuantidadeSementeDestinatario,
        //semente escolhida pelo usuário para trocar pela disponível
        Long semNrIdSementeRemetente,
        Float troNrQuantidadeSementeRemetente
) {
}
