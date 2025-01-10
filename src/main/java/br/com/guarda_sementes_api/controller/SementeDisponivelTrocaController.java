package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.sementedisponiveltroca.SementeDisponivelTrocaService;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.dto.SementeDisponivelTrocaDto;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaFiltroForm;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaForm;
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
@RequestMapping("/sementes-disponiveis-troca")
@RequiredArgsConstructor
@Tag(name = "Sementes Disponíveis para Troca", description = "Operações relacionadas as sementes disponíveis para troca")
public class SementeDisponivelTrocaController {
    private final SementeDisponivelTrocaService sementeDisponivelTrocaService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma Semente nas Disponíveis para Troca", description = "Endpoint responsável por cadastrar uma semente na lista de disponíveis para troca.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = SementeDisponivelTrocaDto.class)))
    public SementeDisponivelTrocaDto cadastrarSementeDisponivelTroca(@RequestBody @Valid SementeDisponivelTrocaForm form) {
        return this.sementeDisponivelTrocaService.cadastrarOuAtualizarSementeDisponivelTroca(null, form);
    }

    @PutMapping("/{sdtNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar uma Semente nas Disponíveis para Troca", description = "Endpoint responsável por atualizar uma semente da lista de disponíveis para troca.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = SementeDisponivelTrocaDto.class)))
    public SementeDisponivelTrocaDto atualizarSementeDisponivelTroca(@PathVariable @Valid Long sdtNrId, @RequestBody @Valid SementeDisponivelTrocaForm form) {
        return this.sementeDisponivelTrocaService.cadastrarOuAtualizarSementeDisponivelTroca(sdtNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Sementes Disponíveis para Troca", description = "Endpoint responsável por listar as sementes disponíveis para troca.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SementeDisponivelTrocaDto.class)))
    public Page<SementeDisponivelTrocaDto> listarSementesDisponiveisParaTroca(SementeDisponivelTrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.sementeDisponivelTrocaService.listarSementesDisponiveisParaTroca(filtro, pageable);
    }

    @GetMapping("/{sdtNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar uma Semente Disponível para Troca por ID", description = "Endpoint responsável por buscar uma semente disponível para troca por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SementeDisponivelTrocaDto.class)))
    public SementeDisponivelTrocaDto obterSementeDisponivelTrocaPorId(@PathVariable @Valid Long sdtNrId) {
        return this.sementeDisponivelTrocaService.obterSementeDisponivelTrocaPorId(sdtNrId);
    }

    @DeleteMapping("/{sdtNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover uma Semente das Disponíveis para Troca", description = "Endpoint responsável por remover uma semente da lista de disponíveis para troca.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarSementeDisponivelTroca(@PathVariable @Valid Long sdtNrId) {
        this.sementeDisponivelTrocaService.deletarSementeDisponivelTroca(sdtNrId);
    }
}
