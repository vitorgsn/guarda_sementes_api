package br.com.guarda_sementes_api.service.sementedisponiveltroca;

import br.com.guarda_sementes_api.model.sementedisponiveltroca.SementeDisponivelTrocaEntidade;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.dto.SementeDisponivelTrocaDto;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaFiltroForm;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SementeDisponivelTrocaService {
    SementeDisponivelTrocaDto cadastrarOuAtualizarSementeDisponivelTroca(Long sdtNrId, SementeDisponivelTrocaForm form);
    Page<SementeDisponivelTrocaDto> listarSementesDisponiveisParaTroca(SementeDisponivelTrocaFiltroForm filtro, Pageable pageable);
    SementeDisponivelTrocaDto obterSementeDisponivelTrocaPorId(Long sdtNrId);
    void deletarSementeDisponivelTroca(Long sdtNrId);
    void indisponibilizarSementeParaTroca(Long sdtNrId);
    SementeDisponivelTrocaEntidade obterSementeDisponivelTrocaPorSemNrId(Long semNrId);
}
