package br.com.guarda_sementes_api.service.troca.impl;

import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import br.com.guarda_sementes_api.model.troca.TrocaSementeRelacionamento;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import br.com.guarda_sementes_api.repository.armazem.ArmazemUsuarioRepository;
import br.com.guarda_sementes_api.repository.troca.TrocaRepository;
import br.com.guarda_sementes_api.repository.troca.TrocaSementeRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.armazem.ArmazemService;
import br.com.guarda_sementes_api.service.armazem.ArmazemUsuarioService;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemForm;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoForm;
import br.com.guarda_sementes_api.service.semente.SementeService;
import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.semente.form.SementeForm;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.SementeDisponivelTrocaService;
import br.com.guarda_sementes_api.service.troca.StatusTrocaService;
import br.com.guarda_sementes_api.service.troca.TrocaSementeService;
import br.com.guarda_sementes_api.service.troca.TrocaService;
import br.com.guarda_sementes_api.service.troca.dto.TrocaComStatusDto;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDto;
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
    private final TrocaSementeRepository trocaSementeRepository;

    private final SementeService sementeService;
    private final ArmazemService armazemService;
    private final ArmazemUsuarioRepository armazemUsuarioRepository;
    private final SementeDisponivelTrocaService sementeDisponivelTrocaService;

    @Override
    @Transactional
    public TrocaDto cadastrarOuAtualizarTroca(UUID troNrId, TrocaForm form) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = troNrId != null ?
                this.trocaRepository.buscarTrocaPorId(troNrId)
                        .orElseThrow(() -> new RuntimeException("Troca não encontrada")
                        ) : new TrocaEntidade();

        if (form.usuNrIdDestinatario() != null) this.usuarioService.buscarUsuarioPorId(form.usuNrIdDestinatario());

        var semente = this.sementeService.obterSementePorId(form.ofertadaParaTroca().semNrId());
        this.validarSaldo(semente, form.ofertadaParaTroca().trsNrQuantidade());

        troca.setTroTxInstruncoes(form.troTxInstruncoes());
        troca.setUsuNrIdDestinatario(form.usuNrIdDestinatario());
        troca.setUsuNrIdRemetente(usuarioAutenticado.getUsuNrId());

        this.trocaRepository.save(troca);

        this.trocaSementeService.cadastrarOuAtualizarTrocaSemente(troca.getTroNrId(), form.ofertadaNasDisponiveis().semNrId(), form.ofertadaNasDisponiveis().trsNrQuantidade());
        if (form.ofertadaParaTroca() != null) {
            this.trocaSementeService.cadastrarOuAtualizarTrocaSemente(troca.getTroNrId(), form.ofertadaParaTroca().semNrId(), form.ofertadaParaTroca().trsNrQuantidade());
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
    public TrocaDto obterTrocaPorId(UUID troNrId) {
        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada."));
        return new TrocaDto(troca);
    }

    @Override
    @Transactional
    public void deletarTroca(UUID troNrId) {
        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada."));

        if (!statusTrocaService.obterStatusAtualDaTroca(troNrId).equals(StatusTrocaEnum.PENDENTE)) {
            throw new RuntimeException("Não foi possível deletar a troca. Só é possível deletar uma troca que esteja PENDENTE.");
        }


        this.trocaRepository.delete(troca);
    }

    @Override
    @Transactional
    public void aceitarTroca(UUID troNrId) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                        .orElseThrow(() -> new RuntimeException("Troca não encontrada"));

        if (!troca.getUsuNrIdDestinatario().equals(usuarioAutenticado.getUsuNrId())) {
            throw new RuntimeException("Você não possui permissão para aceitar a troca.");
        }

        var sementesDaTroca = this.trocaSementeRepository.buscarTrocaSementePorTroNrId(troNrId);

        for (TrocaSementeRelacionamento trocaSemente : sementesDaTroca) {
            var semente = this.sementeService.obterSementePorId(trocaSemente.getSemNrId());

            this.validarSaldo(semente, trocaSemente.getTrsNrQuantidade());

            var armazem = this.armazemService.obterArmazemPorId(semente.armNrId());
            var armazemUsuario = this.armazemUsuarioRepository.buscarArmazemUsuarioPorUsuNrIdEArmNrId(usuarioAutenticado.getUsuNrId(), armazem.armNrId());

            if (armazemUsuario.isPresent()) {
                var novoArmazem = this.armazemService.criarArmazemParaUsuario(new ArmazemForm(armazem.armTxDescricao(), armazem.ctaNrId()), troca.getUsuNrIdRemetente());
                var novaSemente = this.sementeService.cadastrarOuAtualizarSemente(null, new SementeForm(semente.semTxNome(), trocaSemente.getTrsNrQuantidade(), semente.semTxDescricao(), novoArmazem.armNrId()));
            } else {
                var novoArmazem = this.armazemService.cadastrarOuAtualizarArmazem(null, new ArmazemForm(armazem.armTxDescricao(), armazem.ctaNrId()));
                var novaSemente = this.sementeService.cadastrarOuAtualizarSemente(null, new SementeForm(semente.semTxNome(), trocaSemente.getTrsNrQuantidade(), semente.semTxDescricao(), novoArmazem.armNrId()));
            }

            this.sementeService.cadastrarOuAtualizarSemente(semente.semNrId(), new SementeForm(semente.semTxNome(), (semente.semNrQuantidade()-trocaSemente.getTrsNrQuantidade()), semente.semTxDescricao(), semente.armNrId()));

            var sementeDisponivelTroca = this.sementeDisponivelTrocaService.obterSementeDisponivelTrocaPorSemNrId(semente.semNrId());
            if (sementeDisponivelTroca != null) {
                this.sementeDisponivelTrocaService.indisponibilizarSementeParaTroca(sementeDisponivelTroca.getSdtNrId());
            }
        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(StatusTrocaEnum.CONCLUIDA, troca.getTroNrId()));
    }

    @Override
    @Transactional
    public void recusarTroca(UUID troNrId) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada"));

        if (!troca.getUsuNrIdDestinatario().equals(usuarioAutenticado.getUsuNrId())) {
            throw new RuntimeException("Você não possui permissão para recusar a troca.");
        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(StatusTrocaEnum.RECUSADA, troca.getTroNrId()));
    }

    @Override
    @Transactional
    public void cancelarTroca(UUID troNrId) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RuntimeException("Troca não encontrada"));

        if (!troca.getUsuNrIdRemetente().equals(usuarioAutenticado.getUsuNrId())) {
            throw new RuntimeException("Você não possui permissão para cancelar a troca.");
        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(StatusTrocaEnum.CANCELADA, troca.getTroNrId()));
    }

    private void validarSaldo(SementeDto semente, Float quantidadePretendida) {
        if (semente.semNrQuantidade() < quantidadePretendida) {
            throw new RuntimeException("Você não possui estoque suficiente para essa semente.");
        }
    }
}
