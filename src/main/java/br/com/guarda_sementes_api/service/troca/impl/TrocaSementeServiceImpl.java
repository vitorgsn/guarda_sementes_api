package br.com.guarda_sementes_api.service.troca.impl;

import br.com.guarda_sementes_api.model.troca.TrocaSementeRelacionamento;
import br.com.guarda_sementes_api.repository.troca.TrocaSementeRepository;
import br.com.guarda_sementes_api.service.troca.StatusTrocaService;
import br.com.guarda_sementes_api.service.troca.TrocaSementeService;
import br.com.guarda_sementes_api.service.troca.dto.TrocaSementeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrocaSementeServiceImpl implements TrocaSementeService {
    private final TrocaSementeRepository trocaSementeRepository;

    @Override
    public TrocaSementeDto cadastrarOuAtualizarTrocaSemente(UUID troNrId, Long semNrId, Float trsNrQuantidade) {
        var trocaSemente = this.trocaSementeRepository.buscarTrocaSementePorTroNrIdESemNrId(troNrId, semNrId)
                .orElse(TrocaSementeRelacionamento.builder().build());

        trocaSemente.setTroNrId(troNrId);
        trocaSemente.setSemNrId(semNrId);
        trocaSemente.setTrsNrQuantidade(trsNrQuantidade);

        this.trocaSementeRepository.save(trocaSemente);

        return new TrocaSementeDto(trocaSemente);
    }
}
