package br.com.guarda_sementes_api.service.contato;

import br.com.guarda_sementes_api.service.contato.dto.ContatoDto;
import br.com.guarda_sementes_api.service.contato.form.ContatoFiltroForm;
import br.com.guarda_sementes_api.service.contato.form.ContatoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoService {
    ContatoDto cadastrarOuAtualizarContato(Long conNrId, ContatoForm form);
    Page<ContatoDto> listarContatosDoUsuario(ContatoFiltroForm filtro, Pageable pageable);
}
