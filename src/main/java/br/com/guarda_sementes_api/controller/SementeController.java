package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.semente.SementeService;
import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.semente.form.SementeFiltroForm;
import br.com.guarda_sementes_api.service.semente.form.SementeForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sementes")
@RequiredArgsConstructor
public class SementeController {

    private final SementeService sementeService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public SementeDto cadastrarSemente(@RequestBody @Valid SementeForm form) {
        return this.sementeService.cadastrarOuAtualizarSemente(null, form);
    }

    @PutMapping("/{semNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SementeDto atualizarSemente(@PathVariable @Valid Long semNrId, @RequestBody @Valid SementeForm form) {
        return this.sementeService.cadastrarOuAtualizarSemente(semNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<SementeDto> listarSementes(SementeFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.sementeService.listarSementesDoUsuario(filtro, pageable);
    }

    @GetMapping("/{semNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public SementeDto obterSementePorId(@PathVariable @Valid Long semNrId) {
        return this.sementeService.obterSementePorId(semNrId);
    }

    @DeleteMapping("/{semNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarSemente(@PathVariable @Valid Long semNrId) {
        this.sementeService.deletarSemente(semNrId);
    }
}
