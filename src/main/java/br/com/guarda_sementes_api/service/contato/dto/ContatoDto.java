package br.com.guarda_sementes_api.service.contato.dto;

import br.com.guarda_sementes_api.model.contato.ContatoEntidade;

import java.time.LocalDateTime;

public record ContatoDto(
        Long conNrId,
        String conTxEmail,
        String conTxNumero,
        LocalDateTime conDtCreatedAt,
        LocalDateTime conDtUpdateAt,
        Boolean conBlContatoPadrao
) {
    public ContatoDto (ContatoEntidade contato) {
        this(
                contato.getConNrId(),
                contato.getConTxEmail(),
                contato.getConTxNumero(),
                contato.getConDtCreatedAt(),
                contato.getConDtUpdateAt(),
                contato.getConBlContatoPadrao()
        );
    }
}
