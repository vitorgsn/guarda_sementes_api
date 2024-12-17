package br.com.guarda_sementes_api.controller.cidade;

import br.com.guarda_sementes_api.service.cidade.CidadeService;
import br.com.guarda_sementes_api.service.cidade.dto.CidadeDto;
import br.com.guarda_sementes_api.service.cidade.form.CidadeFiltroForm;
import br.com.guarda_sementes_api.service.cidade.form.CidadeForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {
    private final CidadeService cidadeService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public CidadeDto cadastrarCidade(@RequestBody @Valid CidadeForm form) {
        return this.cidadeService.cadastrarOuAtualizarCidade(null, form);
    }

    @PutMapping("/{cidNrID}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CidadeDto atualizarCidade(@PathVariable @Valid Long cidNrID, @RequestBody @Valid CidadeForm form) {
        return this.cidadeService.cadastrarOuAtualizarCidade(cidNrID, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<CidadeDto> listarCidades(CidadeFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.cidadeService.listarCidades(filtro, pageable);
    }

    @GetMapping("/{cidNrID}")
    @ResponseStatus(code = HttpStatus.OK)
    public CidadeDto obterCidadePorId(@PathVariable @Valid Long cidNrID) {
        return this.cidadeService.obterCidadePorId(cidNrID);
    }

    @DeleteMapping("/{cidNrID}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarCidade(@PathVariable @Valid Long cidNrID) {
        this.cidadeService.deletarCidade(cidNrID);
    }
}
