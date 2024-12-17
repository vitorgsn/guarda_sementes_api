package br.com.guarda_sementes_api.service.estado.impl;

import br.com.guarda_sementes_api.repository.estado.EstadoRepository;
import br.com.guarda_sementes_api.service.estado.EstadoService;
import br.com.guarda_sementes_api.service.estado.dto.EstadoDto;
import br.com.guarda_sementes_api.service.estado.form.EstadoFiltroForm;
import br.com.guarda_sementes_api.service.estado.form.EstadoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {
    private final EstadoRepository estadoRepository;


    @Override
    public EstadoDto cadastrarOuAtualizarEstado(Long estNrId, EstadoForm estadoForm) {
        return null;
    }

    @Override
    public Page<EstadoDto> listarEstados(EstadoFiltroForm filtro, Pageable pageable) {
        return null;
    }

    @Override
    public EstadoDto obterEstadoPorId(Long estNrId) {
        return null;
    }

    @Override
    public void deletarEstado(Long estNrId) {

    }
}
