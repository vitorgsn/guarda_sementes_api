package br.com.guarda_sementes_api.service.contato.impl;

import br.com.guarda_sementes_api.model.contato.ContatoEntidade;
import br.com.guarda_sementes_api.model.contato.ContatoUsuarioRelacionamento;
import br.com.guarda_sementes_api.repository.contato.ContatoRepository;
import br.com.guarda_sementes_api.repository.contato.ContatoUsuarioRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.contato.ContatoService;
import br.com.guarda_sementes_api.service.contato.dto.ContatoDto;
import br.com.guarda_sementes_api.service.contato.form.ContatoFiltroForm;
import br.com.guarda_sementes_api.service.contato.form.ContatoForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContatoServiceImpl extends BaseService implements ContatoService {

    private final BaseService baseService;
    private final ContatoRepository contatoRepository;
    private final ContatoUsuarioRepository contatoUsuarioRepository;

    @Override
    public ContatoDto cadastrarOuAtualizarContato(Long conNrId, ContatoForm form) {

        var contato = conNrId != null ?
                this.contatoRepository.findByConNrId(conNrId)
                        .orElseThrow(() -> new RuntimeException("Contato não encontrado")
                ) : new ContatoEntidade();

        contato.setConTxEmail(form.conTxEmail());
        contato.setConTxNumero(form.conTxNumero());

        this.contatoRepository.save(contato);

        var usuarioAutenticado = this.baseService.getUsuarioAutenticado();

        ContatoUsuarioRelacionamento contatoUsuario = new ContatoUsuarioRelacionamento();
        contatoUsuario.setConNrId(contato.getConNrId());
        contatoUsuario.setUsuNrId(usuarioAutenticado.getUsuNrId());

        this.contatoUsuarioRepository.save(contatoUsuario);

        return new ContatoDto(contato);
    }

    @Override
    public Page<ContatoDto> listarContatosDoUsuario(ContatoFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.baseService.getUsuarioAutenticado();
        return this.contatoRepository.listarContatosDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(ContatoDto::new);
    }
}
