package br.com.guarda_sementes_api.service.troca.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.exceptions.StatusNaoEncontradoException;
import br.com.guarda_sementes_api.model.troca.StatusTrocaEntidade;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import br.com.guarda_sementes_api.repository.troca.StatusTrocaRepository;
import br.com.guarda_sementes_api.service.troca.StatusTrocaService;
import br.com.guarda_sementes_api.service.troca.dto.StatusTrocaDto;
import br.com.guarda_sementes_api.service.troca.form.StatusTrocaForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatusTrocaServiceImpl implements StatusTrocaService {
    private final StatusTrocaRepository statusTrocaRepository;


    @Override
    public StatusTrocaDto cadastrarOuAtualizarStatusTroca(Long sttNrId, StatusTrocaForm form) {

        StatusTrocaEntidade statusTroca = new StatusTrocaEntidade();

        statusTroca.setSttTxStatus(form.sttTxStatus());
        statusTroca.setTroNrIdTroca(form.troNrIdTroca());
        statusTroca.setSttDtStatusTroca(LocalDateTime.now());

        this.statusTrocaRepository.save(statusTroca);

        return new StatusTrocaDto(statusTroca);
    }

    @Override
    public StatusTrocaEnum obterStatusAtualDaTroca(UUID troNrId) {

        var status = troNrId != null ?
                this.statusTrocaRepository.obterStatusAtualDaTroca(troNrId) : null;

        if (status == null) {
            throw new StatusNaoEncontradoException("Status não encontrado, por favor contate o suporte do aplicativo");
        }

        return status;
    }
}
