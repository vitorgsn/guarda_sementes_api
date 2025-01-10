package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.armazem.ArmazemService;
import br.com.guarda_sementes_api.service.armazem.dto.ArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemForm;
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
@RequestMapping("/armazens")
@RequiredArgsConstructor
@Tag(name = "Armazéns", description = "Operações relacionadas aos armazéns de sementes do usuário")
public class ArmazemController {
    private final ArmazemService armazemService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um Armazém", description = "Endpoint responsável por cadastrar um armazém.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = ArmazemDto.class)))
    public ArmazemDto cadastrarArmazem(@RequestBody @Valid ArmazemForm form) {
        return this.armazemService.cadastrarOuAtualizarArmazem(null, form);
    }

    @PutMapping("/{armNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar um Armazém", description = "Endpoint responsável por atualizar um armazém.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = ArmazemDto.class)))
    public ArmazemDto atualizarArmazem(@PathVariable @Valid Long armNrId, @RequestBody @Valid ArmazemForm form) {
        return this.armazemService.cadastrarOuAtualizarArmazem(armNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar os Armazéns do Usuário", description = "Endpoint responsável por listar os armazéns do usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ArmazemDto.class)))
    public Page<ArmazemDto> listarArmazens(ArmazemFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.armazemService.listarArmazensDoUsuario(filtro, pageable);
    }

    @GetMapping("/{armNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar um Armazém por ID", description = "Endpoint responsável por buscar um armazém por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ArmazemDto.class)))
    public ArmazemDto obterArmazemPorId(@PathVariable @Valid Long armNrId) {
        return this.armazemService.obterArmazemPorId(armNrId);
    }

    @DeleteMapping("/{armNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar um Armazém", description = "Endpoint responsável por deletar um armazém.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarArmazem(@PathVariable @Valid Long armNrId) {
        this.armazemService.deletarArmazem(armNrId);
    }
}
