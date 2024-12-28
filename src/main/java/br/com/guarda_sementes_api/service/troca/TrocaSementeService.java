package br.com.guarda_sementes_api.service.troca;

import br.com.guarda_sementes_api.service.troca.dto.TrocaSementeDto;

import java.util.UUID;

public interface TrocaSementeService {
    TrocaSementeDto cadastrarOuAtualizarTrocaSemente(UUID troNrId, Long semNrId, Float trsNrQuantidade);
}
