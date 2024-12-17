package br.com.guarda_sementes_api.service.endereco.dto;

import br.com.guarda_sementes_api.model.endereco.EnderecoUsuarioRelacionamento;

import java.util.UUID;

public record EnderecoUsuarioDto(
        Long enuNrId,
        UUID usuNrId,
        Long endNrId
) {
    public EnderecoUsuarioDto(EnderecoUsuarioRelacionamento entidade) {
        this (
                entidade.getEnuNrId(),
                entidade.getUsuNrId(),
                entidade.getEndNrId()
        );
    }
}
