package br.com.guarda_sementes_api.service.endereco.dto;

import br.com.guarda_sementes_api.model.endereco.EnderecoEntidade;

import java.time.LocalDateTime;

public record EnderecoDto(
        Long endNrId,
        String endTxBairro,
        String endTxLogradouro,
        String endTxNumero,
        String endTxReferencia,
        Long cidNrId,
        LocalDateTime endDtCreatedAt,
        LocalDateTime endDtUpdateAt
) {
    public EnderecoDto(EnderecoEntidade entidade) {
        this (
                entidade.getEndNrId(),
                entidade.getEndTxBairro(),
                entidade.getEndTxLogradouro(),
                entidade.getEndTxNumero(),
                entidade.getEndTxReferencia(),
                entidade.getCidNrId(),
                entidade.getEndDtCreatedAt(),
                entidade.getEndDtUpdateAt()
        );
    }
}
