package br.com.guarda_sementes_api.service.semente.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.model.semente.SementeEntidade;
import br.com.guarda_sementes_api.repository.semente.SementeRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.armazem.ArmazemService;
import br.com.guarda_sementes_api.service.semente.SementeService;
import br.com.guarda_sementes_api.service.semente.dto.SementeDto;
import br.com.guarda_sementes_api.service.semente.form.SementeFiltroForm;
import br.com.guarda_sementes_api.service.semente.form.SementeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SementeServiceImpl extends BaseService implements SementeService {
    private final SementeRepository sementeRepository;
    private final ArmazemService armazemService;

    @Override
    public SementeDto cadastrarOuAtualizarSemente(Long semNrId, SementeForm form) {
        var semente = semNrId != null ?
                this.sementeRepository.buscarSementePorId(semNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Semente não encontrada")
                        ) : new SementeEntidade();

        if (form.armNrId() != null) this.armazemService.obterArmazemPorId(form.armNrId());

        semente.setSemTxNome(form.semTxNome());
        semente.setSemNrQuantidade(form.semNrQuantidade());
        semente.setSemTxDescricao(form.semTxDescricao());
        semente.setArmNrId(form.armNrId());

        this.sementeRepository.save(semente);

        return new SementeDto(semente);
    }

    @Override
    public Page<SementeDto> listarSementesDoUsuario(SementeFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.sementeRepository.listarSementesDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(SementeDto::new);
    }

    @Override
    public SementeDto obterSementePorId(Long semNrId) {
        var semente = this.sementeRepository.buscarSementePorId(semNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Semente não encontrada"));
        return new SementeDto(semente);
    }

    @Override
    public void deletarSemente(Long semNrId) {
        var semente = this.sementeRepository.buscarSementePorId(semNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Semente não encontrada"));
        semente.setSemBlAtivo(false);
        this.sementeRepository.save(semente);
    }
}
