package br.com.guarda_sementes_api.model.troca.enuns;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusTroca {
    PENDENTE("Pendente"),
    CONCLUIDA("Concluida"),
    CANCELADA("Cancelada");

    private String status;
}
