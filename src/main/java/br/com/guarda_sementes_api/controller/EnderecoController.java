package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.endereco.EnderecoService;
import br.com.guarda_sementes_api.service.endereco.dto.EnderecoDto;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoFiltroForm;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoForm;
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
public class EnderecoController {
    private final EnderecoService enderecoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public EnderecoDto cadastrarEndereco(@RequestBody @Valid EnderecoForm form) {
        return this.enderecoService.cadastrarOuAtualizarEndereco(null, form);
    }

    @PutMapping("/{endNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EnderecoDto atualizarEndereco(@PathVariable @Valid Long endNrId, @RequestBody @Valid EnderecoForm form) {
        return this.enderecoService.cadastrarOuAtualizarEndereco(endNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<EnderecoDto> listarEnderecosDoUsuario(EnderecoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.enderecoService.listarEnderecosDoUsuario(filtro, pageable);
    }

    @GetMapping("/{endNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public EnderecoDto obterEnderecoPorId(@PathVariable @Valid Long endNrId) {
        return this.enderecoService.obterEnderecoPorId(endNrId);
    }

    @DeleteMapping("/{endNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarEndereco(@PathVariable @Valid Long endNrId) {
        this.enderecoService.deletarEndereco(endNrId);
    }
}
