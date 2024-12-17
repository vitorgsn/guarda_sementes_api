package br.com.guarda_sementes_api.service.contato.impl;

import br.com.guarda_sementes_api.model.contato.ContatoUsuarioRelacionamento;
import br.com.guarda_sementes_api.repository.contato.ContatoUsuarioRepository;
import br.com.guarda_sementes_api.service.contato.ContatoUsuarioService;
import br.com.guarda_sementes_api.service.contato.dto.ContatoUsuarioDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContatoUsuarioServiceImpl implements ContatoUsuarioService {

    private final ContatoUsuarioRepository contatoUsuarioRepository;

    @Override
    public ContatoUsuarioDto cadastrarOuAtualizarContatoUsuario(Long conNrId, UUID usuNrID) {

        var contatoUsuario = this.contatoUsuarioRepository.buscarContatoUsuarioPorUsuNrIdEConNrId(usuNrID, conNrId)
                .orElse(ContatoUsuarioRelacionamento.builder().build());

        contatoUsuario.setConNrId(conNrId);
        contatoUsuario.setUsuNrId(usuNrID);

        this.contatoUsuarioRepository.save(contatoUsuario);

        return new ContatoUsuarioDto(contatoUsuario);
    }
}
