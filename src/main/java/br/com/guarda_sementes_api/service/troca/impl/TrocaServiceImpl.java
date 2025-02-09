package br.com.guarda_sementes_api.service.troca.impl;

import br.com.guarda_sementes_api.exceptions.EstoqueInsuficienteException;
import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import br.com.guarda_sementes_api.repository.troca.TrocaRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.armazem.ArmazemService;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemForm;
import br.com.guarda_sementes_api.service.semente.SementeService;
import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.semente.form.SementeForm;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.SementeDisponivelTrocaService;
import br.com.guarda_sementes_api.service.troca.StatusTrocaService;
import br.com.guarda_sementes_api.service.troca.TrocaService;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDadosCompletosDto;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDto;
import br.com.guarda_sementes_api.service.troca.form.StatusTrocaForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaFiltroForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaForm;
import br.com.guarda_sementes_api.service.usuario.impl.UsuarioServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrocaServiceImpl extends BaseService implements TrocaService {
    private final TrocaRepository trocaRepository;
    private final UsuarioServiceImpl usuarioService;
    private final StatusTrocaService statusTrocaService;

    private final SementeService sementeService;
    private final ArmazemService armazemService;
    private final SementeDisponivelTrocaService sementeDisponivelTrocaService;

    @Override
    @Transactional
    public TrocaDto cadastrarOuAtualizarTroca(UUID troNrId, TrocaForm form) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = troNrId != null ?
                this.trocaRepository.buscarTrocaPorId(troNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Troca não encontrada")
                        ) : new TrocaEntidade();

        if (form.usuNrIdDestinatario() != null) this.usuarioService.buscarUsuarioPorId(form.usuNrIdDestinatario());

        var sementeDasDisponiveis = this.sementeService.obterSementePorId(form.semNrIdSementeDestinatario());
        this.validarSaldo(sementeDasDisponiveis, form.troNrQuantidadeSementeDestinatario());

        var sementeOfertada = this.sementeService.obterSementePorId(form.semNrIdSementeRemetente());
        this.validarSaldo(sementeOfertada, form.troNrQuantidadeSementeRemetente());

        troca.setTroTxInstruncoes(form.troTxInstrucoes());
        troca.setUsuNrIdDestinatario(form.usuNrIdDestinatario());
        troca.setUsuNrIdRemetente(usuarioAutenticado.getUsuNrId());
        troca.setSemNrIdSementeDestinatario(form.semNrIdSementeDestinatario());
        troca.setSemNrIdSementeRemetente(form.semNrIdSementeRemetente());
        troca.setTroNrQuantidadeSementeDestinatario(form.troNrQuantidadeSementeDestinatario());
        troca.setTroNrQuantidadeSementeRemetente(form.troNrQuantidadeSementeRemetente());

        this.trocaRepository.save(troca);

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(
                StatusTrocaEnum.PENDENTE,
                troca.getTroNrId()
        ));

        return new TrocaDto(troca);
    }

    /*
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
    */

    @Override
    public Page<TrocaDadosCompletosDto> listarTrocasDoUsuario(TrocaFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.trocaRepository.listarTrocasDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(TrocaDadosCompletosDto::new);
    }

    @Override
    public TrocaDto obterTrocaPorId(UUID troNrId) {
        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Troca não encontrada."));
        return new TrocaDto(troca);
    }

    @Override
    @Transactional
    public void aceitarTroca(UUID troNrId) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Troca não encontrada"));

        if (!troca.getUsuNrIdDestinatario().equals(usuarioAutenticado.getUsuNrId())) {
            throw new AccessDeniedException("Você não possui permissão para aceitar a troca.");
        }

        // pega a semente da feira e verifica se o saldo atual é suficiente
        var sementeDasDisponiveis = this.sementeService.obterSementePorId(troca.getSemNrIdSementeDestinatario());
        this.validarSaldo(sementeDasDisponiveis, troca.getTroNrQuantidadeSementeDestinatario());

        // pega a semente ofertada na troca e verifica se o saldo atual é suficiente
        var sementeOfertada = this.sementeService.obterSementePorId(troca.getSemNrIdSementeRemetente());
        this.validarSaldo(sementeOfertada, troca.getTroNrQuantidadeSementeRemetente());

        // pega o armazém da semente da feira
        var armazemFeira = this.armazemService.obterArmazemPorId(sementeDasDisponiveis.armNrId());
        // pega o armazém da semente ofertada
        var armazemOfertada = this.armazemService.obterArmazemPorId(sementeOfertada.armNrId());

        // cria um novo armazém para o usuário que ofertou igual ao armazém da semente da feira
        var novoArmazemOfertada = this.armazemService.criarArmazemParaUsuario(new ArmazemForm(armazemFeira.armTxDescricao(), armazemFeira.ctaNrId()), troca.getUsuNrIdRemetente());
        // cria um novo armazém para o usuário dono da semente da feira iguaç ao armazém da semente ofertada
        var novoArmazemFeira = this.armazemService.criarArmazemParaUsuario(new ArmazemForm(armazemOfertada.armTxDescricao(), armazemOfertada.ctaNrId()), troca.getUsuNrIdDestinatario());

        // atualiza a quantidade da semente que está na feira
        this.sementeService.cadastrarOuAtualizarSemente(sementeDasDisponiveis.semNrId(), new SementeForm(sementeDasDisponiveis.semTxNome(), (sementeDasDisponiveis.semNrQuantidade() - troca.getTroNrQuantidadeSementeDestinatario()), sementeDasDisponiveis.semTxDescricao(), sementeDasDisponiveis.armNrId()));
        // cria uma nova semente para o usuário dono da semente na feira, igual a semente ofertada em troca
        this.sementeService.cadastrarOuAtualizarSemente(null, new SementeForm(sementeOfertada.semTxNome(), troca.getTroNrQuantidadeSementeRemetente(), sementeOfertada.semTxDescricao(), novoArmazemFeira.armNrId()));

        // atualiza a quantidade da semente que foi ofertada em troca
        this.sementeService.cadastrarOuAtualizarSemente(sementeOfertada.semNrId(), new SementeForm(sementeOfertada.semTxNome(), (sementeOfertada.semNrQuantidade() - troca.getTroNrQuantidadeSementeRemetente()), sementeOfertada.semTxDescricao(), sementeOfertada.armNrId()));
        // cria uma nova semente para o usuário que ofertou igual a semente da feira
        this.sementeService.cadastrarOuAtualizarSemente(null, new SementeForm(sementeDasDisponiveis.semTxNome(), troca.getTroNrQuantidadeSementeDestinatario(), sementeDasDisponiveis.semTxDescricao(), novoArmazemOfertada.armNrId()));

        var sementeDisponivelTroca = this.sementeDisponivelTrocaService.obterSementeDisponivelTrocaPorSemNrId(sementeDasDisponiveis.semNrId());
        if (sementeDisponivelTroca != null) {
            this.sementeDisponivelTrocaService.indisponibilizarSementeParaTroca(sementeDisponivelTroca.getSdtNrId());
        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(StatusTrocaEnum.CONCLUIDA, troca.getTroNrId()));
    }

    @Override
    @Transactional
    public void recusarTroca(UUID troNrId) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Troca não encontrada"));

        if (!troca.getUsuNrIdDestinatario().equals(usuarioAutenticado.getUsuNrId())) {
            throw new AccessDeniedException("Você não possui permissão para recusar a troca.");
        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(StatusTrocaEnum.RECUSADA, troca.getTroNrId()));
    }

    @Override
    @Transactional
    public void cancelarTroca(UUID troNrId) {
        var usuarioAutenticado = this.getUsuarioAutenticado();

        var troca = this.trocaRepository.buscarTrocaPorId(troNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Troca não encontrada"));

        if (!troca.getUsuNrIdRemetente().equals(usuarioAutenticado.getUsuNrId())) {
            throw new AccessDeniedException("Você não possui permissão para cancelar a troca.");
        }

        if (!statusTrocaService.obterStatusAtualDaTroca(troNrId).equals(StatusTrocaEnum.PENDENTE)) {
            throw new AccessDeniedException("Não foi possível cancelar a troca. Só é possível cancelar uma troca que esteja PENDENTE.");
        }

        this.statusTrocaService.cadastrarOuAtualizarStatusTroca(null, new StatusTrocaForm(StatusTrocaEnum.CANCELADA, troca.getTroNrId()));
    }

    private void validarSaldo(SementeDto semente, Float quantidadePretendida) {
        if (semente.semNrQuantidade() < quantidadePretendida) {
            throw new EstoqueInsuficienteException("Você não possui estoque suficiente para essa semente.");
        }
    }
}
