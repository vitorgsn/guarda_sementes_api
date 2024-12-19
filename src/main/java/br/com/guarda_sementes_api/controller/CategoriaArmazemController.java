package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.armazem.CategoriaArmazemService;
import br.com.guarda_sementes_api.service.armazem.dto.CategoriaArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemForm;
import br.com.guarda_sementes_api.service.estado.dto.EstadoDto;
import br.com.guarda_sementes_api.service.estado.form.EstadoForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias-armazem")
@RequiredArgsConstructor
public class CategoriaArmazemController {
    private final CategoriaArmazemService categoriaArmazemService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoriaArmazemDto cadastrarCategoriaArmazem(@RequestBody @Valid CategoriaArmazemForm form) {
        return this.categoriaArmazemService.cadastrarOuAtualizarCategoriaArmazem(null, form);
    }

    @PutMapping("/{ctaNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoriaArmazemDto atualizarCategoriaArmazem(@PathVariable @Valid Long ctaNrId, @RequestBody @Valid CategoriaArmazemForm form) {
        return this.categoriaArmazemService.cadastrarOuAtualizarCategoriaArmazem(ctaNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<CategoriaArmazemDto> listarCategoriasArmazem(CategoriaArmazemFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.categoriaArmazemService.listarCategoriasArmazem(filtro, pageable);
    }

    @GetMapping("/{ctaNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public CategoriaArmazemDto obterCategoriaArmazemPorId(@PathVariable @Valid Long ctaNrId) {
        return this.categoriaArmazemService.obterCategoriaArmazemPorId(ctaNrId);
    }

    @DeleteMapping("/{ctaNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarCategoriaArmazem(@PathVariable @Valid Long ctaNrId) {
        this.categoriaArmazemService.deletarCategoriaArmazem(ctaNrId);
    }
}
