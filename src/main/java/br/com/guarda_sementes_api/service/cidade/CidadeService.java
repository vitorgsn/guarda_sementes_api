package br.com.guarda_sementes_api.service.cidade;

import br.com.guarda_sementes_api.service.cidade.dto.CidadeDadosCompletosDto;
import br.com.guarda_sementes_api.service.cidade.dto.CidadeDto;
import br.com.guarda_sementes_api.service.cidade.form.CidadeFiltroForm;
import br.com.guarda_sementes_api.service.cidade.form.CidadeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CidadeService {
    CidadeDto cadastrarOuAtualizarCidade(Long cidNrID, CidadeForm cidadeForm);
    Page<CidadeDadosCompletosDto> listarCidades(CidadeFiltroForm filtro, Pageable pageable);
    CidadeDto obterCidadePorId(Long cidNrID);
    void deletarCidade(Long cidNrID);
}
