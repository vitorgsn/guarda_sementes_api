package br.com.guarda_sementes_api.service.troca.form;

import java.util.UUID;

public record TrocaFiltroForm(
        UUID troNrId,
        String troTxInstruncoes
) {
}
