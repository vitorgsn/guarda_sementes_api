package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.authentication.dto.AutenticacaoDto;
import br.com.guarda_sementes_api.service.usuario.dto.UsuarioDto;
import br.com.guarda_sementes_api.service.authentication.form.AutenticacaoForm;
import br.com.guarda_sementes_api.service.usuario.form.UsuarioForm;
import br.com.guarda_sementes_api.service.authentication.impl.AuthenticationServiceImpl;
import br.com.guarda_sementes_api.service.usuario.impl.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas aos Usuários")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;
    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/autenticar")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Autenticar um Usuário", description = "Endpoint responsável por autenticar um usuário.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = AutenticacaoDto.class)))
    public ResponseEntity<AutenticacaoDto> autenticar(@RequestBody @Valid AutenticacaoForm form) {
        return ResponseEntity.ok(this.authenticationServiceImpl.autenticarUsuario(form));
    }

    @PostMapping("/registrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Cadastrar um Usuário", description = "Endpoint responsável por cadastrar um usuário.")
    @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(schema = @Schema(implementation = UsuarioDto.class)))
    public UsuarioDto registrar(@RequestBody @Valid UsuarioForm form) {
        return this.usuarioServiceImpl.cadastrarOuAtualizarUsuario(null, form);
    }

    @PostMapping("/{usuNrId}/inativar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(summary = "Inativar um Usuário", description = "Endpoint responsável por inativar um usuário.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public void registrar(@PathVariable @Valid UUID usuNrId) {
        this.usuarioServiceImpl.inativarOuAtivarUsuario(usuNrId);
    }

    @GetMapping("/perfil")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Buscar o Perfil do Usuário Autenticado", description = "Endpoint responsável por buscar o perfil do usuário autenticado.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UsuarioDto.class)))
    public UsuarioDto perfil() {
        return this.usuarioServiceImpl.buscarPerfil();
    }
}
