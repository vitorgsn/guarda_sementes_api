package br.com.guarda_sementes_api.service.instrucao.impl;

import br.com.guarda_sementes_api.model.instrucao.InstrucaoEntidade;
import br.com.guarda_sementes_api.repository.instrucao.InstrucaoRepository;
import br.com.guarda_sementes_api.service.instrucao.InstrucaoService;
import br.com.guarda_sementes_api.service.instrucao.dto.InstrucaoDto;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoFiltroForm;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrucaoServiceImpl implements InstrucaoService {

    private final InstrucaoRepository instrucaoRepository;

    @Override
    public InstrucaoDto cadastrarOuAtualizarInstrucao(Long insNrId, InstrucaoForm form) {

        var instrucao = insNrId != null ?
                this.instrucaoRepository.buscarInstrucaoPorId(insNrId)
                        .orElseThrow(() -> new RuntimeException("Instrução não encontrada.")
                ) : new InstrucaoEntidade();

        instrucao.setInsTxTitulo(form.insTxTitulo());
        instrucao.setInsTxInstrucao(form.insTxInstrucao());

        this.instrucaoRepository.save(instrucao);

        return new InstrucaoDto(instrucao);
    }

    @Override
    public Page<InstrucaoDto> listarInstrucoes(InstrucaoFiltroForm filtro, Pageable pageable) {
        return this.instrucaoRepository.listarInstrucoes(filtro, pageable).map(InstrucaoDto::new);
    }

    @Override
    public InstrucaoDto obterInstrucaoPorId(Long insNrId) {
        var instrucao = this.instrucaoRepository.buscarInstrucaoPorId(insNrId)
                .orElseThrow(() -> new RuntimeException("Instrução não encontrada."));
        return new InstrucaoDto(instrucao);
    }

    @Override
    public void deletarInstrucao(Long insNrId) {
        var instrucao = this.instrucaoRepository.buscarInstrucaoPorId(insNrId)
                .orElseThrow(() -> new RuntimeException("Instrução não encontrada."));
        this.instrucaoRepository.delete(instrucao);
    }
}
