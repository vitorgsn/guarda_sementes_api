package br.com.guarda_sementes_api.service.authentication.dto;

public record LoginDto(String accessToken, Long expiresIn) {
}
