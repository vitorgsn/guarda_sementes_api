package br.com.guarda_sementes_api.service.authentication.impl;

import br.com.guarda_sementes_api.config.security.TokenService;
import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import br.com.guarda_sementes_api.service.authentication.dto.AutenticacaoDto;
import br.com.guarda_sementes_api.service.authentication.form.AutenticacaoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacaoDto autenticarUsuario (AutenticacaoForm form) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(form.login(), form.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        UsuarioEntidade usuario = (UsuarioEntidade) auth.getPrincipal();

        var token = this.tokenService.gerarToken(usuario);

        return new AutenticacaoDto(usuario.getUsuTxNome(), usuario.getUsuNrId(), token);
    }
}
