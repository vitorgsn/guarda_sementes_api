package br.com.guarda_sementes_api.service.troca.impl;

import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import br.com.guarda_sementes_api.repository.troca.TrocaRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.troca.StatusTrocaService;
import br.com.guarda_sementes_api.service.troca.TrocaSementeService;
import br.com.guarda_sementes_api.service.troca.TrocaService;
import br.com.guarda_sementes_api.service.troca.dto.TrocaComStatusDto;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDto;
import br.com.guarda_sementes_api.service.troca.form.SementeTrocaForm;
import br.com.guarda_sementes_api.service.troca.form.StatusTrocaForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaFiltroForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaForm;
import br.com.guarda_sementes_api.service.usuario.impl.UsuarioServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrocaServiceImpl extends BaseService implements TrocaService {
    private final TrocaRepository trocaRepository;
    private final UsuarioServiceImpl usuarioService;
    private final TrocaSementeService trocaSementeService;
    private final StatusTrocaService statusTrocaService;

    @Override
    @Transactional
    public TrocaDto cadastrarOuAtualizarTroca(UUID troNrId, TrocaForm form) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = troNrId != null ?
                this.trocaRepository.buscarTrocaPorId(troNrId)
                        .orElseThrow(() -> new RuntimeException("Troca não encontrada")
                        ) : new TrocaEntidade();

        if (form.usuNrIdDestinatario() != null) this.usuarioService.buscarUsuarioPorId(form.usuNrIdDestinatario());

        troca.setTroTxInstruncoes(form.troTxInstruncoes());
        troca.setUsuNrIdDestinatario(form.usuNrIdDestinatario());
        troca.setUsuNrIdRemetente(usuarioAutenticado.getUsuNrId());

        this.trocaRepository.save(troca);

        for (SementeTrocaForm sementes : form.sementesTroca()) {
            this.trocaSementeService.cadastrarOuAtualizarTrocaSemente(troca.getTroNrId(), sementes.semNrId(), sementes.trsNrQuantidade());

        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(
                StatusTrocaEnum.PENDENTE,
                troca.getTroNrId()
        ));

        return new TrocaDto(troca);
    }

    @Override
    public Page<TrocaComStatusDto> listarTrocasDoUsuarioRemetente(TrocaFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.trocaRepository.listarTrocasDoUsuarioRemetente(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(TrocaComStatusDto::new);
    }

    @Override
    public Page<TrocaComStatusDto> listarTrocasDoUsuarioDestinatario(TrocaFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.trocaRepository.listarTrocasDoUsuarioDestinatario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(TrocaComStatusDto::new);
    }

    @Override
    public TrocaDto obterTrocaPorId(UUID troNrID) {
        var troca = this.trocaRepository.buscarTrocaPorId(troNrID)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada."));
        return new TrocaDto(troca);
    }

    @Override
    @Transactional
    public void deletarTroca(UUID troNrID) {
        var troca = this.trocaRepository.buscarTrocaPorId(troNrID)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada."));

        if (!statusTrocaService.obterStatusAtualDaTroca(troNrID).equals(StatusTrocaEnum.PENDENTE)) {
            throw new RuntimeException("Não foi possível deletar a troca. Só é possível deletar uma troca que esteja PENDENTE.");
        }

        this.trocaRepository.delete(troca);
    }
}
