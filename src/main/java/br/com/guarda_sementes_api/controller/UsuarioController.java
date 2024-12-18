package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.authentication.dto.AutenticacaoDto;
import br.com.guarda_sementes_api.service.usuario.dto.UsuarioDto;
import br.com.guarda_sementes_api.service.authentication.form.AutenticacaoForm;
import br.com.guarda_sementes_api.service.usuario.form.UsuarioForm;
import br.com.guarda_sementes_api.service.authentication.impl.AuthenticationServiceImpl;
import br.com.guarda_sementes_api.service.usuario.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;
    private final AuthenticationServiceImpl authenticationServiceImpl;

    @PostMapping("/autenticar")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AutenticacaoDto> autenticar(@RequestBody @Valid AutenticacaoForm form) {
        return ResponseEntity.ok(this.authenticationServiceImpl.autenticarUsuario(form));
    }

    @PostMapping("/registrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioDto registrar(@RequestBody @Valid UsuarioForm form) {
        return this.usuarioServiceImpl.cadastrarOuAtualizarUsuario(null, form);
    }

    @PostMapping("/{usuNrId}/inativar")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void registrar(@PathVariable @Valid UUID usuNrId) {
        this.usuarioServiceImpl.inativarOuAtivarUsuario(usuNrId);
    }
}
