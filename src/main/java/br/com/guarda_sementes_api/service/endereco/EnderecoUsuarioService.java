package br.com.guarda_sementes_api.service.endereco;

import br.com.guarda_sementes_api.service.endereco.dto.EnderecoUsuarioDto;

import java.util.UUID;

public interface EnderecoUsuarioService {
    EnderecoUsuarioDto cadastrarOuAtualizarEnderecoUsuario(Long endNrId, UUID usuNrID);
}
