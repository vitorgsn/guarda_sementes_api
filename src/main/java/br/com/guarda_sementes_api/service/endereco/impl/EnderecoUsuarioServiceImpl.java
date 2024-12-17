package br.com.guarda_sementes_api.service.endereco.impl;

import br.com.guarda_sementes_api.model.endereco.EnderecoUsuarioRelacionamento;
import br.com.guarda_sementes_api.repository.endereco.EnderecoUsuarioRepository;
import br.com.guarda_sementes_api.service.endereco.EnderecoUsuarioService;
import br.com.guarda_sementes_api.service.endereco.dto.EnderecoUsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoUsuarioServiceImpl implements EnderecoUsuarioService {
    private final EnderecoUsuarioRepository enderecoUsuarioRepository;

    @Override
    public EnderecoUsuarioDto cadastrarOuAtualizarEnderecoUsuario(Long endNrId, UUID usuNrID) {

        var enderecoUsuario = this.enderecoUsuarioRepository.buscarEnderecoUsuarioPorUsuNrIdEEndNrId(usuNrID, endNrId)
                .orElse(EnderecoUsuarioRelacionamento.builder().build());

        enderecoUsuario.setUsuNrId(usuNrID);
        enderecoUsuario.setEndNrId(endNrId);

        this.enderecoUsuarioRepository.save(enderecoUsuario);

        return new EnderecoUsuarioDto(enderecoUsuario);
    }
}
