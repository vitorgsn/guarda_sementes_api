package br.com.guarda_sementes_api.service.troca.dto;

import br.com.guarda_sementes_api.model.troca.TrocaSementeRelacionamento;

import java.util.UUID;

public record TrocaSementeDto(
        Long trsNrId,
        UUID troNrId,
        Long semNrId
) {
    public TrocaSementeDto (TrocaSementeRelacionamento entidade){
        this(
                entidade.getTrsNrId(),
                entidade.getTroNrId(),
                entidade.getSemNrId()
        );
    }
}
