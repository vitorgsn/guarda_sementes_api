package br.com.guarda_sementes_api.service.usuario.dto;

import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;

import java.util.UUID;

public record UsuarioDto(
        UUID usuNrId,
        String usuTxNome,
        String usuTxLogin,
        Boolean usuBlAtivo
) {
    public UsuarioDto (UsuarioEntidade entidade) {
        this(
                entidade.getUsuNrId(),
                entidade.getUsuTxNome(),
                entidade.getUsuTxLogin(),
                entidade.isUsuBlAtivo()
        );
    }
}
