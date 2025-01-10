package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.cidade.CidadeService;
import br.com.guarda_sementes_api.service.cidade.dto.CidadeDto;
import br.com.guarda_sementes_api.service.cidade.form.CidadeFiltroForm;
import br.com.guarda_sementes_api.service.cidade.form.CidadeForm;
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
@RequestMapping("/cidades")
@RequiredArgsConstructor
@Tag(name = "Cidades", description = "Operações relacionadas as cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma Cidade", description = "Endpoint responsável por cadastrar uma cidade.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CidadeDto.class)))
    public CidadeDto cadastrarCidade(@RequestBody @Valid CidadeForm form) {
        return this.cidadeService.cadastrarOuAtualizarCidade(null, form);
    }

    @PutMapping("/{cidNrID}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar uma Cidade", description = "Endpoint responsável por atualizar uma cidade.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CidadeDto.class)))
    public CidadeDto atualizarCidade(@PathVariable @Valid Long cidNrID, @RequestBody @Valid CidadeForm form) {
        return this.cidadeService.cadastrarOuAtualizarCidade(cidNrID, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar as Cidades", description = "Endpoint responsável por listar as cidades.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CidadeDto.class)))
    public Page<CidadeDto> listarCidades(CidadeFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.cidadeService.listarCidades(filtro, pageable);
    }

    @GetMapping("/{cidNrID}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar uma Cidade por ID", description = "Endpoint responsável por buscar uma cidade por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CidadeDto.class)))
    public CidadeDto obterCidadePorId(@PathVariable @Valid Long cidNrID) {
        return this.cidadeService.obterCidadePorId(cidNrID);
    }

    @DeleteMapping("/{cidNrID}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar uma Cidade", description = "Endpoint responsável por deletar uma cidade.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarCidade(@PathVariable @Valid Long cidNrID) {
        this.cidadeService.deletarCidade(cidNrID);
    }
}
