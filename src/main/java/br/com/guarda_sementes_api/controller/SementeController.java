package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.semente.SementeService;
import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.semente.form.SementeFiltroForm;
import br.com.guarda_sementes_api.service.semente.form.SementeForm;
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
@RequestMapping("/sementes")
@RequiredArgsConstructor
@Tag(name = "Sementes", description = "Operações relacionadas as sementes dos usuários")
public class SementeController {

    private final SementeService sementeService;


    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma Semente", description = "Endpoint responsável por cadastrar uma semente.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = SementeDto.class)))
    public SementeDto cadastrarSemente(@RequestBody @Valid SementeForm form) {
        return this.sementeService.cadastrarOuAtualizarSemente(null, form);
    }

    @PutMapping("/{semNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar uma Semente", description = "Endpoint responsável por atualizar uma semente.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = SementeDto.class)))
    public SementeDto atualizarSemente(@PathVariable @Valid Long semNrId, @RequestBody @Valid SementeForm form) {
        return this.sementeService.cadastrarOuAtualizarSemente(semNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Sementes do Usuário", description = "Endpoint responsável por listar as sementes do usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SementeDto.class)))
    public Page<SementeDto> listarSementes(SementeFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.sementeService.listarSementesDoUsuario(filtro, pageable);
    }

    @GetMapping("/{semNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar uma Semente por ID", description = "Endpoint responsável por buscar uma semente por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = SementeDto.class)))
    public SementeDto obterSementePorId(@PathVariable @Valid Long semNrId) {
        return this.sementeService.obterSementePorId(semNrId);
    }

    @DeleteMapping("/{semNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar uma Semente", description = "Endpoint responsável por deletar uma semente.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarSemente(@PathVariable @Valid Long semNrId) {
        this.sementeService.deletarSemente(semNrId);
    }
}
