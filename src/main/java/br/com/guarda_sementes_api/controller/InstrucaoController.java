package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.instrucao.InstrucaoService;
import br.com.guarda_sementes_api.service.instrucao.dto.InstrucaoDto;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoFiltroForm;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrucoes")
@RequiredArgsConstructor
public class InstrucaoController {

    private final InstrucaoService instrucaoService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public InstrucaoDto cadastrarInstrucao(@RequestBody @Valid InstrucaoForm form) {
        return this.instrucaoService.cadastrarOuAtualizarInstrucao(null, form);
    }

    @PutMapping("/{insNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public InstrucaoDto atualizarInstrucao(@PathVariable @Valid Long insNrId, @RequestBody @Valid InstrucaoForm form) {
        return this.instrucaoService.cadastrarOuAtualizarInstrucao(insNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<InstrucaoDto> listarInstrucoes(InstrucaoFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.instrucaoService.listarInstrucoes(filtro, pageable);
    }

    @GetMapping("/{insNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public InstrucaoDto obterInstrucaoPorId(@PathVariable @Valid Long insNrId) {
        return this.instrucaoService.obterInstrucaoPorId(insNrId);
    }

    @DeleteMapping("/{insNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarInstrucao(@PathVariable @Valid Long insNrId) {
        this.instrucaoService.deletarInstrucao(insNrId);
    }
}
