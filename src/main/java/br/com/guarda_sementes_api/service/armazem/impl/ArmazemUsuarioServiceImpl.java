package br.com.guarda_sementes_api.service.armazem.impl;

import br.com.guarda_sementes_api.model.armazem.ArmazemUsuarioRelacionamento;
import br.com.guarda_sementes_api.repository.armazem.ArmazemUsuarioRepository;
import br.com.guarda_sementes_api.service.armazem.ArmazemUsuarioService;
import br.com.guarda_sementes_api.service.armazem.dto.ArmazemUsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArmazemUsuarioServiceImpl implements ArmazemUsuarioService {
    private final ArmazemUsuarioRepository armazemUsuarioRepository;

    @Override
    public ArmazemUsuarioDto cadastrarOuAtualizarArmazemUsuario(Long armNrId, UUID usuNrID) {
        var armazemUsuario = this.armazemUsuarioRepository.buscarArmazemUsuarioPorUsuNrIdEArmNrId(usuNrID, armNrId)
                .orElse(ArmazemUsuarioRelacionamento.builder().build());

        armazemUsuario.setUsuNrId(usuNrID);
        armazemUsuario.setArmNrId(armNrId);

        this.armazemUsuarioRepository.save(armazemUsuario);

        return new ArmazemUsuarioDto(armazemUsuario);
    }
}
