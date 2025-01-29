package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.endereco.EnderecoService;
import br.com.guarda_sementes_api.service.endereco.dto.EnderecoDto;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoFiltroForm;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoForm;
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
@RequestMapping("/enderecos")
@RequiredArgsConstructor
@Tag(name = "Endereços", description = "Operações relacionadas aos endereços do usuário")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um Endereço", description = "Endpoint responsável por cadastrar um endereço.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = EnderecoDto.class)))
    public EnderecoDto cadastrarEndereco(@RequestBody @Valid EnderecoForm form) {
        return this.enderecoService.cadastrarOuAtualizarEndereco(null, form);
    }

    @PutMapping("/{endNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Atualizar um Endereço", description = "Endpoint responsável por atualizar um endereço.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = EnderecoDto.class)))
    public EnderecoDto atualizarEndereco(@PathVariable @Valid Long endNrId, @RequestBody @Valid EnderecoForm form) {
        return this.enderecoService.cadastrarOuAtualizarEndereco(endNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Listar Endereços do Usuário", description = "Endpoint responsável por listar os endereços do usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EnderecoDto.class)))
    public Page<EnderecoDto> listarEnderecosDoUsuario(EnderecoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.enderecoService.listarEnderecosDoUsuario(filtro, pageable);
    }

    @GetMapping("/{endNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar um Endereço por ID", description = "Endpoint responsável por buscar um endereço por ID.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EnderecoDto.class)))
    public EnderecoDto obterEnderecoPorId(@PathVariable @Valid Long endNrId) {
        return this.enderecoService.obterEnderecoPorId(endNrId);
    }

    @DeleteMapping("/{endNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar um Endereço do Usuário", description = "Endpoint responsável por deletar um endereço do usuário.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void deletarEndereco(@PathVariable @Valid Long endNrId) {
        this.enderecoService.deletarEndereco(endNrId);
    }

    @GetMapping("/padrao")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar o Endereço Padrão do Usuário", description = "Endpoint responsável por buscar o endereço padrão do usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EnderecoDto.class)))
    public EnderecoDto buscarEnderecoPadrao() {
        return this.enderecoService.buscarEnderecoPadrao();
    }
}
