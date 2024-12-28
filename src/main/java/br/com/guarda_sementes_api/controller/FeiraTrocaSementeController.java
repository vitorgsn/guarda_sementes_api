package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.feiratrocasemente.FeiraTrocaSementeService;
import br.com.guarda_sementes_api.service.feiratrocasemente.dto.FeiraTrocaSementeDto;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeFiltroForm;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feira-troca-sementes")
@RequiredArgsConstructor
public class FeiraTrocaSementeController {
    private final FeiraTrocaSementeService feiraTrocaSementeService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public FeiraTrocaSementeDto cadastrarFeiraTrocaSemente(@RequestBody @Valid FeiraTrocaSementeForm form) {
        return this.feiraTrocaSementeService.cadastrarOuAtualizarFeiraTrocaSemente(null, form);
    }

    @PutMapping("/{ftsNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public FeiraTrocaSementeDto atualizarFeiraTrocaSemente(@PathVariable @Valid Long ftsNrId, @RequestBody @Valid FeiraTrocaSementeForm form) {
        return this.feiraTrocaSementeService.cadastrarOuAtualizarFeiraTrocaSemente(ftsNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<FeiraTrocaSementeDto> listarSementesDisponiveisParaTroca(FeiraTrocaSementeFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.feiraTrocaSementeService.listarSementesDisponiveisParaTroca(filtro, pageable);
    }

    @GetMapping("/{ftsNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public FeiraTrocaSementeDto obterFeiraTrocaSementePorId(@PathVariable @Valid Long ftsNrId) {
        return this.feiraTrocaSementeService.obterFeiraTrocaSementePorId(ftsNrId);
    }

    @DeleteMapping("/{ftsNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarFeiraTrocaSemente(@PathVariable @Valid Long ftsNrId) {
        this.feiraTrocaSementeService.deletarFeiraTrocaSemente(ftsNrId);
    }
}
