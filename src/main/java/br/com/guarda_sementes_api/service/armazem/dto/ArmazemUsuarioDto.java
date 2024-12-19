package br.com.guarda_sementes_api.service.armazem.dto;

import br.com.guarda_sementes_api.model.armazem.ArmazemUsuarioRelacionamento;

import java.util.UUID;

public record ArmazemUsuarioDto(
        Long amuNrId,
        UUID usuNrId,
        Long armNrId
) {
    public ArmazemUsuarioDto(ArmazemUsuarioRelacionamento entidade) {
        this (
                entidade.getAmuNrId(),
                entidade.getUsuNrId(),
                entidade.getArmNrId()
        );
    }
}
