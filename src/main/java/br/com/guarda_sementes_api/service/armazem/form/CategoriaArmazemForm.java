package br.com.guarda_sementes_api.service.armazem.form;

import jakarta.validation.constraints.NotBlank;

public record CategoriaArmazemForm(
        @NotBlank(message = "Por favor, informe o nome da Categoria Armazem")
        String ctaTxNome,
        String ctaTxDescricao
) {
}
