package br.com.guarda_sementes_api.service.armazem;

import br.com.guarda_sementes_api.service.armazem.dto.ArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArmazemService {
    ArmazemDto cadastrarOuAtualizarArmazem(Long armNrId, ArmazemForm form);
    Page<ArmazemDto> listarArmazensDoUsuario(ArmazemFiltroForm filtro, Pageable pageable);
    ArmazemDto obterArmazemPorId(Long armNrId);
    void deletarArmazem(Long armNrId);
}
