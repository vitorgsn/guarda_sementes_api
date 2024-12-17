package br.com.guarda_sementes_api.service.estado;

import br.com.guarda_sementes_api.service.estado.dto.EstadoDto;
import br.com.guarda_sementes_api.service.estado.form.EstadoFiltroForm;
import br.com.guarda_sementes_api.service.estado.form.EstadoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstadoService {
    EstadoDto cadastrarOuAtualizarEstado(Long estNrId, EstadoForm estadoForm);
    Page<EstadoDto> listarEstados(EstadoFiltroForm filtro, Pageable pageable);
    EstadoDto obterEstadoPorId(Long estNrId);
    void deletarEstado(Long estNrId);
}
