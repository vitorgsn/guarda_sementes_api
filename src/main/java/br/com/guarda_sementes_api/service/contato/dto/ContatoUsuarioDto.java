package br.com.guarda_sementes_api.service.contato.dto;

import br.com.guarda_sementes_api.model.contato.ContatoUsuarioRelacionamento;

import java.util.UUID;

public record ContatoUsuarioDto(
        Long couNrId,
        UUID usuNrId,
        Long conNrId
) {
    public ContatoUsuarioDto(ContatoUsuarioRelacionamento entidade) {
        this (
                entidade.getCouNrId(),
                entidade.getUsuNrId(),
                entidade.getConNrId()
        );
    }
}
