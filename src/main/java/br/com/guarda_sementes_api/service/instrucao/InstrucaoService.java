package br.com.guarda_sementes_api.service.instrucao;

import br.com.guarda_sementes_api.service.instrucao.dto.InstrucaoDto;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoFiltroForm;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InstrucaoService {
    InstrucaoDto cadastrarOuAtualizarInstrucao(Long insNrId, InstrucaoForm form);
    Page<InstrucaoDto> listarInstrucoes(InstrucaoFiltroForm filtro, Pageable pageable);
    InstrucaoDto obterInstrucaoPorId(Long insNrId);
    void deletarInstrucao(Long insNrId);
}
