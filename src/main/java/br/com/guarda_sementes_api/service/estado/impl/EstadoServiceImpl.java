package br.com.guarda_sementes_api.service.estado.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.model.estado.EstadoEntidade;
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
    public EstadoDto cadastrarOuAtualizarEstado(Long estNrId, EstadoForm form) {

        var estado = estNrId != null ?
                this.estadoRepository.buscarEstadoPorId(estNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Estado não encontrado")
                        ) : new EstadoEntidade();

        estado.setEstTxSigla(form.estTxSigla());
        estado.setEstTxNome(form.estTxNome());

        this.estadoRepository.save(estado);

        return new EstadoDto(estado);
    }

    @Override
    public Page<EstadoDto> listarEstados(EstadoFiltroForm filtro, Pageable pageable) {
        return this.estadoRepository.listarEstados(filtro, pageable).map(EstadoDto::new);
    }

    @Override
    public EstadoDto obterEstadoPorId(Long estNrId) {
        var estado = this.estadoRepository.buscarEstadoPorId(estNrId).orElseThrow(() -> new RegistroNaoEncontradoException("Estado não encontrado."));
        return new EstadoDto(estado);
    }

    @Override
    public void deletarEstado(Long estNrId) {
        var estado = this.estadoRepository.buscarEstadoPorId(estNrId).orElseThrow(() -> new RegistroNaoEncontradoException("Estado não encontrado."));
        this.estadoRepository.delete(estado);
    }
}
