package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.armazem.ArmazemService;
import br.com.guarda_sementes_api.service.armazem.dto.ArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/armazens")
@RequiredArgsConstructor
public class ArmazemController {
    private final ArmazemService armazemService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public ArmazemDto cadastrarArmazem(@RequestBody @Valid ArmazemForm form) {
        return this.armazemService.cadastrarOuAtualizarArmazem(null, form);
    }

    @PutMapping("/{armNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ArmazemDto atualizarArmazem(@PathVariable @Valid Long armNrId, @RequestBody @Valid ArmazemForm form) {
        return this.armazemService.cadastrarOuAtualizarArmazem(armNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<ArmazemDto> listarArmazens(ArmazemFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.armazemService.listarArmazensDoUsuario(filtro, pageable);
    }

    @GetMapping("/{armNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public ArmazemDto obterArmazemPorId(@PathVariable @Valid Long armNrId) {
        return this.armazemService.obterArmazemPorId(armNrId);
    }

    @DeleteMapping("/{armNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarArmazem(@PathVariable @Valid Long armNrId) {
        this.armazemService.deletarArmazem(armNrId);
    }
}
