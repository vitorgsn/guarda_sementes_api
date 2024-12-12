package br.com.guarda_sementes_api.controller.usuario;

import br.com.guarda_sementes_api.config.security.TokenService;
import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import br.com.guarda_sementes_api.service.usuario.dto.AutenticacaoDto;
import br.com.guarda_sementes_api.service.usuario.dto.UsuarioDto;
import br.com.guarda_sementes_api.service.usuario.form.AutenticacaoForm;
import br.com.guarda_sementes_api.service.usuario.form.UsuarioForm;
import br.com.guarda_sementes_api.service.usuario.impl.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/autenticar")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<AutenticacaoDto> autenticar(@RequestBody @Valid AutenticacaoForm form) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(form.login(), form.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        UsuarioEntidade usuario = (UsuarioEntidade) auth.getPrincipal();

        var token = this.tokenService.gerarToken(usuario);

        AutenticacaoDto autenticacaoDto = new AutenticacaoDto(usuario.getUsuTxNome(), usuario.getUsuNrId(), token);

        return ResponseEntity.ok(autenticacaoDto);
    }

    @PostMapping("/registrar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioDto registrar(@RequestBody @Valid UsuarioForm form) {
        return this.usuarioServiceImpl.cadastrarOuAtualizarUsuario(null, form);
    }
}
