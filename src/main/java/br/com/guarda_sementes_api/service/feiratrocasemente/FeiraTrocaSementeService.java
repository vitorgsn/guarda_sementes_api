package br.com.guarda_sementes_api.service.feiratrocasemente;

import br.com.guarda_sementes_api.service.feiratrocasemente.dto.FeiraTrocaSementeDto;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeFiltroForm;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeiraTrocaSementeService {
    FeiraTrocaSementeDto cadastrarOuAtualizarFeiraTrocaSemente(Long ftsNrId, FeiraTrocaSementeForm form);
    Page<FeiraTrocaSementeDto> listarSementesDisponiveisParaTroca(FeiraTrocaSementeFiltroForm filtro, Pageable pageable);
    FeiraTrocaSementeDto obterFeiraTrocaSementePorId(Long ftsNrId);
    void deletarFeiraTrocaSemente(Long ftsNrId);
}
