package br.com.guarda_sementes_api.service.usuario.form;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoForm(
        @NotBlank(message = "O campo Usuário é obrigatório")
        String login,
        @NotBlank(message = "O campo Senha é obrigatório")
        String senha
) {
}
