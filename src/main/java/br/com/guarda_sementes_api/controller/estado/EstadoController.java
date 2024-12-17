package br.com.guarda_sementes_api.controller.estado;

import br.com.guarda_sementes_api.service.estado.EstadoService;
import br.com.guarda_sementes_api.service.estado.dto.EstadoDto;
import br.com.guarda_sementes_api.service.estado.form.EstadoFiltroForm;
import br.com.guarda_sementes_api.service.estado.form.EstadoForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {
    private final EstadoService estadoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public EstadoDto cadastrarEstado(@RequestBody @Valid EstadoForm form) {
        return this.estadoService.cadastrarOuAtualizarEstado(null, form);
    }

    @PutMapping("/{estNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EstadoDto atualizarEstado(@PathVariable @Valid Long estNrId, @RequestBody @Valid EstadoForm form) {
        return this.estadoService.cadastrarOuAtualizarEstado(estNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<EstadoDto> listarEstados(EstadoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.estadoService.listarEstados(filtro, pageable);
    }

    @GetMapping("/{estNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public EstadoDto obterEstadoPorId(@PathVariable @Valid Long estNrId) {
        return this.estadoService.obterEstadoPorId(estNrId);
    }

    @DeleteMapping("/{estNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarEstado(@PathVariable @Valid Long estNrId) {
        this.estadoService.deletarEstado(estNrId);
    }
}
