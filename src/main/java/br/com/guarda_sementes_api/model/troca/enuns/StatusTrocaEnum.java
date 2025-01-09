package br.com.guarda_sementes_api.model.troca.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusTrocaEnum {
    PENDENTE("Pendente"),
    CONCLUIDA("Concluída"),
    RECUSADA("Recusada"),
    CANCELADA("Cancelada");

    private final String status;
}
