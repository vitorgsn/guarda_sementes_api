package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.troca.TrocaService;
import br.com.guarda_sementes_api.service.troca.dto.TrocaComStatusDto;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDto;
import br.com.guarda_sementes_api.service.troca.form.TrocaFiltroForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trocas")
@RequiredArgsConstructor
public class TrocaController {
    private final TrocaService trocaService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public TrocaDto cadastrarTroca(@RequestBody @Valid TrocaForm form) {
        return this.trocaService.cadastrarOuAtualizarTroca(null, form);
    }

    @PutMapping("/{troNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public TrocaDto atualizarTroca(@PathVariable @Valid UUID troNrId, @RequestBody @Valid TrocaForm form) {
        return this.trocaService.cadastrarOuAtualizarTroca(troNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<TrocaComStatusDto> listarTrocasDoUsuarioRemetente(TrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.trocaService.listarTrocasDoUsuarioRemetente(filtro, pageable);
    }

    @GetMapping("/destinatario")
    @ResponseStatus(code = HttpStatus.OK)
    public Page<TrocaComStatusDto> listarTrocasDoUsuarioDestinatario(TrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.trocaService.listarTrocasDoUsuarioDestinatario(filtro, pageable);
    }

    @GetMapping("/{troNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public TrocaDto obterTrocaPorId(@PathVariable @Valid UUID troNrId) {
        return this.trocaService.obterTrocaPorId(troNrId);
    }

    @DeleteMapping("/{troNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarTroca(@PathVariable @Valid UUID troNrId) {
        this.trocaService.deletarTroca(troNrId);
    }
}
