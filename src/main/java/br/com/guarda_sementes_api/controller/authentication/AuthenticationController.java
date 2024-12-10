package br.com.guarda_sementes_api.controller.authentication;

import br.com.guarda_sementes_api.service.authentication.dto.LoginDto;
import br.com.guarda_sementes_api.service.authentication.form.LoginForm;
import br.com.guarda_sementes_api.service.authentication.impl.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public LoginDto login(@RequestBody @Validated LoginForm loginForm) {
        return this.authenticationService.loadUserByUsername(loginForm.usuario());
    }
}
