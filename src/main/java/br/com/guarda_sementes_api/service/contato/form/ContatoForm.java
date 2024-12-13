package br.com.guarda_sementes_api.service.contato.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContatoForm(
        @NotBlank(message = "Por favor, informe um email de contato") @Email
        String conTxEmail,
        @NotBlank(message = "Por favor, informe um número de contato")
        String conTxNumero
) {
}
