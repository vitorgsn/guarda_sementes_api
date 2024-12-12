package br.com.guarda_sementes_api.service.usuario.dto;

import java.util.UUID;

public record AutenticacaoDto(
        String usuTxNome,
        UUID usuNrId,
        String token
) {
    public AutenticacaoDto (String usuTxNome, UUID usuNrId, String token) {
        this.usuTxNome = usuTxNome;
        this.usuNrId = usuNrId;
        this.token = token;
    }
}
