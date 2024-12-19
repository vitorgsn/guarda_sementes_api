package br.com.guarda_sementes_api.service.armazem;

import br.com.guarda_sementes_api.service.armazem.dto.ArmazemUsuarioDto;

import java.util.UUID;

public interface ArmazemUsuarioService {
    ArmazemUsuarioDto cadastrarOuAtualizarArmazemUsuario(Long armNrId, UUID usuNrID);
}
