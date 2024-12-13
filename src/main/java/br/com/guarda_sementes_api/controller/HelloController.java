package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    private final BaseService baseService;

    @GetMapping
    public String sayHello() {

        return baseService.getUsuarioAutenticado().getUsername();
    }
}
