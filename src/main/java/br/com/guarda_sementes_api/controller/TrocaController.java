package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.troca.TrocaService;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDadosCompletosDto;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDto;
import br.com.guarda_sementes_api.service.troca.form.TrocaFiltroForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaForm;
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

import java.util.UUID;

@RestController
@RequestMapping("/trocas")
@RequiredArgsConstructor
@Tag(name = "Trocas", description = "Operações relacionadas as Trocas de Sementes entre os Usuários")
public class TrocaController {
    private final TrocaService trocaService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar uma Troca", description = "Endpoint responsável por cadastrar uma troca.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = TrocaDto.class)))
    public TrocaDto cadastrarTroca(@RequestBody @Valid TrocaForm form) {
        return this.trocaService.cadastrarOuAtualizarTroca(null, form);
    }

    @PutMapping("/{troNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar uma Troca", description = "Endpoint responsável por atualizar uma troca.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = TrocaDto.class)))
    public TrocaDto atualizarTroca(@PathVariable @Valid UUID troNrId, @RequestBody @Valid TrocaForm form) {
        return this.trocaService.cadastrarOuAtualizarTroca(troNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Trocas do Usuário", description = "Endpoint responsável por listar as trocas do usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TrocaDadosCompletosDto.class)))
    public Page<TrocaDadosCompletosDto> listarTrocas(TrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.trocaService.listarTrocasDoUsuario(filtro, pageable);
    }

    /*
    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Trocas abertas pelo Usuário", description = "Endpoint responsável por listar as trocas abertas pelo usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TrocaDto.class)))
    public Page<TrocaComStatusDto> listarTrocasDoUsuarioRemetente(TrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.trocaService.listarTrocasDoUsuarioRemetente(filtro, pageable);
    }

    @GetMapping("/destinatario")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Trocas recebidas pelo Usuário", description = "Endpoint responsável por listar as trocas recebidas pelo usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TrocaDto.class)))
    public Page<TrocaComStatusDto> listarTrocasDoUsuarioDestinatario(TrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.trocaService.listarTrocasDoUsuarioDestinatario(filtro, pageable);
    }
    */

    @GetMapping("/{troNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar uma Troca por ID", description = "Endpoint responsável por buscar uma troca por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = TrocaDto.class)))
    public TrocaDto obterTrocaPorId(@PathVariable @Valid UUID troNrId) {
        return this.trocaService.obterTrocaPorId(troNrId);
    }

    @PostMapping("/{troNrId}/aceitar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Aceitar uma Troca", description = "Endpoint responsável por aceitar uma troca.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void aceitarTroca(@PathVariable @Valid UUID troNrId) {
        this.trocaService.aceitarTroca(troNrId);
    }

    @PostMapping("/{troNrId}/recusar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Recusar uma Troca", description = "Endpoint responsável por recusar uma troca.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void recusarTroca(@PathVariable @Valid UUID troNrId) {
        this.trocaService.recusarTroca(troNrId);
    }

    @PostMapping("/{troNrId}/cancelar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Cancelar uma Troca", description = "Endpoint responsável por cancelar uma troca.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void cancelarTroca(@PathVariable @Valid UUID troNrId) {
        this.trocaService.cancelarTroca(troNrId);
    }
}
