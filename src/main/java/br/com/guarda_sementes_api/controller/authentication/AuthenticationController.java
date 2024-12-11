package br.com.guarda_sementes_api.controller.authentication;

import br.com.guarda_sementes_api.service.authentication.form.AutenticacaoForm;
import br.com.guarda_sementes_api.service.authentication.impl.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/autenticar")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity autenticar(@RequestBody @Valid AutenticacaoForm form) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(form.usuario(), form.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok(null);
    }
}
