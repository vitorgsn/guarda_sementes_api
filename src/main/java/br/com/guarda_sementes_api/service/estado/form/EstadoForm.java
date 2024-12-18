package br.com.guarda_sementes_api.service.estado.form;

import jakarta.validation.constraints.NotBlank;

public record EstadoForm(
        @NotBlank(message = "Por favor, informe a Sigla do Estado")
        String estTxSigla,
        @NotBlank(message = "Por favor, informe o Nome do Estado")
        String estTxNome
) {
}
