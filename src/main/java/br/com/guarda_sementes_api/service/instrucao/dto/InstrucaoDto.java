package br.com.guarda_sementes_api.service.instrucao.dto;

import br.com.guarda_sementes_api.model.instrucao.InstrucaoEntidade;

import java.time.LocalDateTime;

public record InstrucaoDto(
        Long insNrId,
        String insTxTitulo,
        String insTxInstrucao,
        LocalDateTime insDtCreatedAt,
        LocalDateTime insDtUpdateAt,
        Boolean insBlAtivo
) {
    public InstrucaoDto(InstrucaoEntidade entidade) {
        this(
                entidade.getInsNrId(),
                entidade.getInsTxTitulo(),
                entidade.getInsTxInstrucao(),
                entidade.getInsDtCreatedAt(),
                entidade.getInsDtUpdateAt(),
                entidade.getInsBlAtivo()
        );
    }
}
