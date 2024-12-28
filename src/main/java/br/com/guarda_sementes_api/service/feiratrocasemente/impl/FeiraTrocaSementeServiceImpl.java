package br.com.guarda_sementes_api.service.feiratrocasemente.impl;

import br.com.guarda_sementes_api.model.feiratrocasemente.FeiraTrocaSementeEntidade;
import br.com.guarda_sementes_api.repository.feiratrocasemente.FeiraTrocaSementeRepository;
import br.com.guarda_sementes_api.service.feiratrocasemente.FeiraTrocaSementeService;
import br.com.guarda_sementes_api.service.feiratrocasemente.dto.FeiraTrocaSementeDto;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeFiltroForm;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeForm;
import br.com.guarda_sementes_api.service.semente.SementeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeiraTrocaSementeServiceImpl implements FeiraTrocaSementeService {
    private final FeiraTrocaSementeRepository feiraTrocaSementeRepository;
    private final SementeService sementeService;

    @Override
    @Transactional
    public FeiraTrocaSementeDto cadastrarOuAtualizarFeiraTrocaSemente(Long ftsNrId, FeiraTrocaSementeForm form) {

        var feiraTrocaSemente = ftsNrId != null ?
                this.feiraTrocaSementeRepository.buscarFeiraTrocaSementePorId(ftsNrId)
                        .orElseThrow(() -> new RuntimeException("Semente indisponível para troca.")
                ) : new FeiraTrocaSementeEntidade();

        var semente = form.semNrIdSemente() != null ? this.sementeService.obterSementePorId(form.semNrIdSemente()) : null;

        if (semente != null) {
            if (semente.semNrQuantidade() < form.ftsNrQuantidade()) {
                throw new RuntimeException("Não foi possível disponibilizar a semente para troca, estoque insuficiente");
            }
        }

        feiraTrocaSemente.setSemNrIdSemente(form.semNrIdSemente());
        feiraTrocaSemente.setFtsNrQuantidade(form.ftsNrQuantidade());

        this.feiraTrocaSementeRepository.save(feiraTrocaSemente);

        return new FeiraTrocaSementeDto(feiraTrocaSemente);
    }

    @Override
    public Page<FeiraTrocaSementeDto> listarSementesDisponiveisParaTroca(FeiraTrocaSementeFiltroForm filtro, Pageable pageable) {
        return this.feiraTrocaSementeRepository.listarSementesDisponiveisParaTroca(filtro, pageable).map(FeiraTrocaSementeDto::new);
    }

    @Override
    public FeiraTrocaSementeDto obterFeiraTrocaSementePorId(Long ftsNrId) {

        var feiraTrocaSemente = this.feiraTrocaSementeRepository.buscarFeiraTrocaSementePorId(ftsNrId)
                .orElseThrow(() -> new RuntimeException("Semente indisponível para troca."));

        return new FeiraTrocaSementeDto(feiraTrocaSemente);
    }

    @Override
    public void deletarFeiraTrocaSemente(Long ftsNrId) {

        var feiraTrocaSemente = this.feiraTrocaSementeRepository.buscarFeiraTrocaSementePorId(ftsNrId)
                .orElseThrow(() -> new RuntimeException("Semente indisponível para troca."));

        feiraTrocaSemente.setFtsBlDisponivel(false);
        this.feiraTrocaSementeRepository.save(feiraTrocaSemente);
    }
}
