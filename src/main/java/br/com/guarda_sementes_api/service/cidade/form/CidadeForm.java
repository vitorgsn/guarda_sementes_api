package br.com.guarda_sementes_api.service.cidade.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CidadeForm(
        @NotBlank(message = "Por favor, informe o Nome da Cidade")
        String cidTxNome,
        @NotNull(message = "Por favor, informe o Estado")
        Long estNrId
) {
}
