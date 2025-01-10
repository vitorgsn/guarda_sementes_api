package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.estado.EstadoService;
import br.com.guarda_sementes_api.service.estado.dto.EstadoDto;
import br.com.guarda_sementes_api.service.estado.form.EstadoFiltroForm;
import br.com.guarda_sementes_api.service.estado.form.EstadoForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Estados", description = "Operações relacionadas aos Estados (UF)")
public class EstadoController {
    private final EstadoService estadoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um Estado", description = "Endpoint responsável por cadastrar um estado.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = EstadoDto.class)))
    public EstadoDto cadastrarEstado(@RequestBody @Valid EstadoForm form) {
        return this.estadoService.cadastrarOuAtualizarEstado(null, form);
    }

    @PutMapping("/{estNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar um Estado", description = "Endpoint responsável por atualizar um estado.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = EstadoDto.class)))
    public EstadoDto atualizarEstado(@PathVariable @Valid Long estNrId, @RequestBody @Valid EstadoForm form) {
        return this.estadoService.cadastrarOuAtualizarEstado(estNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar os Estados", description = "Endpoint responsável por listar os estados.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstadoDto.class)))
    public Page<EstadoDto> listarEstados(EstadoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.estadoService.listarEstados(filtro, pageable);
    }

    @GetMapping("/{estNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar um Estado por ID", description = "Endpoint responsável por buscar um estado por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstadoDto.class)))
    public EstadoDto obterEstadoPorId(@PathVariable @Valid Long estNrId) {
        return this.estadoService.obterEstadoPorId(estNrId);
    }

    @DeleteMapping("/{estNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar um Estado", description = "Endpoint responsável por deletar um estado.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarEstado(@PathVariable @Valid Long estNrId) {
        this.estadoService.deletarEstado(estNrId);
    }
}
