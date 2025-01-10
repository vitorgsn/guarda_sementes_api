package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.contato.ContatoService;
import br.com.guarda_sementes_api.service.contato.dto.ContatoDto;
import br.com.guarda_sementes_api.service.contato.form.ContatoFiltroForm;
import br.com.guarda_sementes_api.service.contato.form.ContatoForm;
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
@RequestMapping("/contatos")
@RequiredArgsConstructor
@Tag(name = "Contatos", description = "Operações relacionadas aos contatos do usuário")
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um Contato", description = "Endpoint responsável por cadastrar um contato.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = ContatoDto.class)))
    public ContatoDto cadastrarContato(@RequestBody @Valid ContatoForm form) {
        return this.contatoService.cadastrarOuAtualizarContato(null, form);
    }

    @PutMapping("/{conNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar um Contato", description = "Endpoint responsável por atualizar um contato.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = ContatoDto.class)))
    public ContatoDto atualizarContato(@PathVariable @Valid Long conNrId, @RequestBody @Valid ContatoForm form) {
        return this.contatoService.cadastrarOuAtualizarContato(conNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar os Contatos do Usuário", description = "Endpoint responsável por listar os contatos do usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContatoDto.class)))
    public Page<ContatoDto> listarContatosDoUsuario(ContatoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.contatoService.listarContatosDoUsuario(filtro, pageable);
    }

    @GetMapping("/{conNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar um Contato por ID", description = "Endpoint responsável por buscar um contato por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ContatoDto.class)))
    public ContatoDto obterContatoPorId(@PathVariable @Valid Long conNrId) {
        return this.contatoService.obterContatoPorId(conNrId);
    }

    @DeleteMapping("/{conNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar um Contato", description = "Endpoint responsável por deletar um contato.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarContato(@PathVariable @Valid Long conNrId) {
        this.contatoService.deletarContato(conNrId);
    }
}
