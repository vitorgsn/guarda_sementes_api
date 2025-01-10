package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.instrucao.InstrucaoService;
import br.com.guarda_sementes_api.service.instrucao.dto.InstrucaoDto;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoFiltroForm;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoForm;
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
@RequestMapping("/instrucoes")
@RequiredArgsConstructor
@Tag(name = "Instruções", description = "Operações relacionadas as mensagens de Instruções de Troca")
public class InstrucaoController {

    private final InstrucaoService instrucaoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma Instrução", description = "Endpoint responsável por cadastrar uma instrução.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = InstrucaoDto.class)))
    public InstrucaoDto cadastrarInstrucao(@RequestBody @Valid InstrucaoForm form) {
        return this.instrucaoService.cadastrarOuAtualizarInstrucao(null, form);
    }

    @PutMapping("/{insNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar uma Instrução", description = "Endpoint responsável por atualizar uma instrução.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = InstrucaoDto.class)))
    public InstrucaoDto atualizarInstrucao(@PathVariable @Valid Long insNrId, @RequestBody @Valid InstrucaoForm form) {
        return this.instrucaoService.cadastrarOuAtualizarInstrucao(insNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Instruções", description = "Endpoint responsável por listar as instruções.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = InstrucaoDto.class)))
    public Page<InstrucaoDto> listarInstrucoes(InstrucaoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.instrucaoService.listarInstrucoes(filtro, pageable);
    }

    @GetMapping("/{insNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar uma Instrução por ID", description = "Endpoint responsável por buscar uma instrução por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = InstrucaoDto.class)))
    public InstrucaoDto obterInstrucaoPorId(@PathVariable @Valid Long insNrId) {
        return this.instrucaoService.obterInstrucaoPorId(insNrId);
    }

    @DeleteMapping("/{insNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar uma Instrução", description = "Endpoint responsável por deletar uma instrução.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarInstrucao(@PathVariable @Valid Long insNrId) {
        this.instrucaoService.deletarInstrucao(insNrId);
    }
}
