package br.com.guarda_sementes_api.service.sementedisponiveltroca.impl;

import br.com.guarda_sementes_api.model.sementedisponiveltroca.SementeDisponivelTrocaEntidade;
import br.com.guarda_sementes_api.repository.sementedisponiveltroca.SementeDisponivelTrocaRepository;
import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.SementeDisponivelTrocaService;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.dto.SementeDisponivelTrocaDto;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaFiltroForm;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaForm;
import br.com.guarda_sementes_api.service.semente.SementeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SementeDisponivelTrocaServiceImpl implements SementeDisponivelTrocaService {
    private final SementeDisponivelTrocaRepository sementeDisponivelTrocaRepository;
    private final SementeService sementeService;

    @Override
    @Transactional
    public SementeDisponivelTrocaDto cadastrarOuAtualizarSementeDisponivelTroca(Long sdtNrId, SementeDisponivelTrocaForm form) {

        var sementeDisponivelTroca = sdtNrId != null ?
                this.sementeDisponivelTrocaRepository.buscarSementeDisponivelTrocaPorId(sdtNrId)
                        .orElseThrow(() -> new RuntimeException("Semente indisponível para troca.")
                ) : new SementeDisponivelTrocaEntidade();

        var semente = form.semNrIdSemente() != null ? this.sementeService.obterSementePorId(form.semNrIdSemente()) : null;

        if (semente != null) {
            if (semente.semNrQuantidade() < form.sdtNrQuantidade()) {
                throw new RuntimeException("Não foi possível disponibilizar a semente para troca, estoque insuficiente");
            }
        }

        sementeDisponivelTroca.setSemNrIdSemente(form.semNrIdSemente());
        sementeDisponivelTroca.setSdtNrQuantidade(form.sdtNrQuantidade());
        sementeDisponivelTroca.setSdtTxObservacoes(form.sdtTxObservacoes());

        this.sementeDisponivelTrocaRepository.save(sementeDisponivelTroca);

        return new SementeDisponivelTrocaDto(sementeDisponivelTroca);
    }

    @Override
    public Page<SementeDisponivelTrocaDto> listarSementesDisponiveisParaTroca(SementeDisponivelTrocaFiltroForm filtro, Pageable pageable) {
        return this.sementeDisponivelTrocaRepository.listarSementesDisponiveisParaTroca(filtro, pageable).map(SementeDisponivelTrocaDto::new);
    }

    @Override
    public SementeDisponivelTrocaDto obterSementeDisponivelTrocaPorId(Long sdtNrId) {

        var sementeDisponivelTroca = this.sementeDisponivelTrocaRepository.buscarSementeDisponivelTrocaPorId(sdtNrId)
                .orElseThrow(() -> new RuntimeException("Semente indisponível para troca."));

        return new SementeDisponivelTrocaDto(sementeDisponivelTroca);
    }

    @Override
    public void deletarSementeDisponivelTroca(Long sdtNrId) {

        var sementeDisponivelTroca = this.sementeDisponivelTrocaRepository.buscarSementeDisponivelTrocaPorId(sdtNrId)
                .orElseThrow(() -> new RuntimeException("Semente indisponível para troca."));

        sementeDisponivelTroca.setSdtBlDisponivel(false);
        this.sementeDisponivelTrocaRepository.save(sementeDisponivelTroca);
    }

    @Override
    public void indisponibilizarSementeParaTroca(Long sdtNrId) {
        var sementeDisponivelTroca = this.sementeDisponivelTrocaRepository.buscarSementeDisponivelTrocaPorId(sdtNrId)
                .orElseThrow(() -> new RuntimeException("Semente indisponível para troca."));

        sementeDisponivelTroca.setSdtBlDisponivel(false);
        this.sementeDisponivelTrocaRepository.save(sementeDisponivelTroca);
    }

    @Override
    public SementeDisponivelTrocaEntidade obterSementeDisponivelTrocaPorSemNrId(Long semNrId) {
        return this.sementeDisponivelTrocaRepository.buscarSementeDisponivelTrocaPorSemNrId(semNrId);
    }
}
