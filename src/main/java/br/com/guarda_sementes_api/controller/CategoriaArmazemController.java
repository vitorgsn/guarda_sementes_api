package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.armazem.CategoriaArmazemService;
import br.com.guarda_sementes_api.service.armazem.dto.CategoriaArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemForm;
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
@RequestMapping("/categorias-armazem")
@RequiredArgsConstructor
@Tag(name = "Categorias de Armazém", description = "Operações relacionadas as categorias de armazém")
public class CategoriaArmazemController {
    private final CategoriaArmazemService categoriaArmazemService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma Categoria de Armazém", description = "Endpoint responsável por cadastrar uma categoria de armazém.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CategoriaArmazemDto.class)))
    public CategoriaArmazemDto cadastrarCategoriaArmazem(@RequestBody @Valid CategoriaArmazemForm form) {
        return this.categoriaArmazemService.cadastrarOuAtualizarCategoriaArmazem(null, form);
    }

    @PutMapping("/{ctaNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar uma Categoria de Armazém", description = "Endpoint responsável por atualizar uma categoria de armazém.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = CategoriaArmazemDto.class)))
    public CategoriaArmazemDto atualizarCategoriaArmazem(@PathVariable @Valid Long ctaNrId, @RequestBody @Valid CategoriaArmazemForm form) {
        return this.categoriaArmazemService.cadastrarOuAtualizarCategoriaArmazem(ctaNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar as Categorias de Armazém", description = "Endpoint responsável por listar as categorias de armazém.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoriaArmazemDto.class)))
    public Page<CategoriaArmazemDto> listarCategoriasArmazem(CategoriaArmazemFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.categoriaArmazemService.listarCategoriasArmazem(filtro, pageable);
    }

    @GetMapping("/{ctaNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar uma Categoria de Armazém por ID", description = "Endpoint responsável por buscar uma categoria de armazém por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoriaArmazemDto.class)))
    public CategoriaArmazemDto obterCategoriaArmazemPorId(@PathVariable @Valid Long ctaNrId) {
        return this.categoriaArmazemService.obterCategoriaArmazemPorId(ctaNrId);
    }

    @DeleteMapping("/{ctaNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar uma Categoria de Armazém", description = "Endpoint responsável por deletar uma categoria de armazém.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarCategoriaArmazem(@PathVariable @Valid Long ctaNrId) {
        this.categoriaArmazemService.deletarCategoriaArmazem(ctaNrId);
    }
}
