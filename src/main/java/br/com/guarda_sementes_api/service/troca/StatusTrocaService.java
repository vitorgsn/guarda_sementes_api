package br.com.guarda_sementes_api.service.troca;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import br.com.guarda_sementes_api.service.troca.dto.StatusTrocaDto;
import br.com.guarda_sementes_api.service.troca.form.StatusTrocaForm;

import java.util.UUID;

public interface StatusTrocaService {
    StatusTrocaDto cadastrarOuAtualizarStatusTroca(Long sttNrId, StatusTrocaForm form);
    StatusTrocaEnum obterStatusAtualDaTroca(UUID troNrId);
}
