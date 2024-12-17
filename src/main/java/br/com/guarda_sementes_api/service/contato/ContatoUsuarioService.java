package br.com.guarda_sementes_api.service.contato;

import br.com.guarda_sementes_api.service.contato.dto.ContatoUsuarioDto;

import java.util.UUID;

public interface ContatoUsuarioService {
    ContatoUsuarioDto cadastrarOuAtualizarContatoUsuario(Long conNrId, UUID usuNrID);
}
