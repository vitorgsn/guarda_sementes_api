package br.com.guarda_sementes_api.service.semente;

import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.semente.form.SementeFiltroForm;
import br.com.guarda_sementes_api.service.semente.form.SementeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SementeService {
    SementeDto cadastrarOuAtualizarSemente(Long semNrId, SementeForm form);
    Page<SementeDto> listarSementesDoUsuario(SementeFiltroForm filtro, Pageable pageable);
    SementeDto obterSementePorId(Long semNrId);
    void deletarSemente(Long semNrId);
}
