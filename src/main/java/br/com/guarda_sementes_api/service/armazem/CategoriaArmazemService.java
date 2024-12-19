package br.com.guarda_sementes_api.service.armazem;

import br.com.guarda_sementes_api.service.armazem.dto.CategoriaArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaArmazemService {
    CategoriaArmazemDto cadastrarOuAtualizarCategoriaArmazem(Long ctaNrId, CategoriaArmazemForm form);
    Page<CategoriaArmazemDto> listarCategoriasArmazem(CategoriaArmazemFiltroForm filtro, Pageable pageable);
    CategoriaArmazemDto obterCategoriaArmazemPorId(Long ctaNrId);
    void deletarCategoriaArmazem(Long ctaNrId);
}
