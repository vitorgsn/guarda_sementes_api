package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.contato.ContatoService;
import br.com.guarda_sementes_api.service.contato.dto.ContatoDto;
import br.com.guarda_sementes_api.service.contato.form.ContatoFiltroForm;
import br.com.guarda_sementes_api.service.contato.form.ContatoForm;
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
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ContatoDto cadastrarContato(@RequestBody @Valid ContatoForm form) {
        return this.contatoService.cadastrarOuAtualizarContato(null, form);
    }

    @PutMapping("/{conNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ContatoDto atualizarContato(@PathVariable @Valid Long conNrId, @RequestBody @Valid ContatoForm form) {
        return this.contatoService.cadastrarOuAtualizarContato(conNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ContatoDto> listarContatosDoUsuario(ContatoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.contatoService.listarContatosDoUsuario(filtro, pageable);
    }

    @GetMapping("/{conNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ContatoDto obterContatoPorId(@PathVariable @Valid Long conNrId) {
        return this.contatoService.obterContatoPorId(conNrId);
    }

    @DeleteMapping("/{conNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarContato(@PathVariable @Valid Long conNrId) {
        this.contatoService.deletarContato(conNrId);
    }
}
