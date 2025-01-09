package br.com.guarda_sementes_api.controller;

import br.com.guarda_sementes_api.service.sementedisponiveltroca.SementeDisponivelTrocaService;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.dto.SementeDisponivelTrocaDto;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaFiltroForm;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sementes-disponiveis-troca")
@RequiredArgsConstructor
public class SementeDisponivelTrocaController {
    private final SementeDisponivelTrocaService sementeDisponivelTrocaService;

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public SementeDisponivelTrocaDto cadastrarSementeDisponivelTroca(@RequestBody @Valid SementeDisponivelTrocaForm form) {
        return this.sementeDisponivelTrocaService.cadastrarOuAtualizarSementeDisponivelTroca(null, form);
    }

    @PutMapping("/{sdtNrId}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SementeDisponivelTrocaDto atualizarSementeDisponivelTroca(@PathVariable @Valid Long sdtNrId, @RequestBody @Valid SementeDisponivelTrocaForm form) {
        return this.sementeDisponivelTrocaService.cadastrarOuAtualizarSementeDisponivelTroca(sdtNrId, form);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public Page<SementeDisponivelTrocaDto> listarSementesDisponiveisParaTroca(SementeDisponivelTrocaFiltroForm filtro, @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return this.sementeDisponivelTrocaService.listarSementesDisponiveisParaTroca(filtro, pageable);
    }

    @GetMapping("/{sdtNrId}")
    @ResponseStatus(code = HttpStatus.OK)
    public SementeDisponivelTrocaDto obterSementeDisponivelTrocaPorId(@PathVariable @Valid Long sdtNrId) {
        return this.sementeDisponivelTrocaService.obterSementeDisponivelTrocaPorId(sdtNrId);
    }

    @DeleteMapping("/{sdtNrId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarSementeDisponivelTroca(@PathVariable @Valid Long sdtNrId) {
        this.sementeDisponivelTrocaService.deletarSementeDisponivelTroca(sdtNrId);
    }
}
