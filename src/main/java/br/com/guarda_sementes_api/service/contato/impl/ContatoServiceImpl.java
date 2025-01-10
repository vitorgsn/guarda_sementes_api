package br.com.guarda_sementes_api.service.contato.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.model.contato.ContatoEntidade;
import br.com.guarda_sementes_api.repository.contato.ContatoRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.contato.ContatoService;
import br.com.guarda_sementes_api.service.contato.ContatoUsuarioService;
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
    private final ContatoRepository contatoRepository;
    private final ContatoUsuarioService contatoUsuarioService;

    @Override
    public ContatoDto cadastrarOuAtualizarContato(Long conNrId, ContatoForm form) {

        var contato = conNrId != null ?
                this.contatoRepository.buscarContatoPorId(conNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Contato não encontrado")
                ) : new ContatoEntidade();

        contato.setConTxEmail(form.conTxEmail());
        contato.setConTxNumero(form.conTxNumero());

        this.contatoRepository.save(contato);

        var usuarioAutenticado = this.getUsuarioAutenticado();

        this.contatoUsuarioService.cadastrarOuAtualizarContatoUsuario(contato.getConNrId(), usuarioAutenticado.getUsuNrId());

        return new ContatoDto(contato);
    }

    @Override
    public Page<ContatoDto> listarContatosDoUsuario(ContatoFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.contatoRepository.listarContatosDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(ContatoDto::new);
    }

    @Override
    public ContatoDto obterContatoPorId(Long conNrId) {
        var contato = this.contatoRepository.buscarContatoPorId(conNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Contato não encontrado."));

        return new ContatoDto(contato);
    }

    @Override
    public void deletarContato(Long conNrId) {
        var contato = this.contatoRepository.buscarContatoPorId(conNrId).orElseThrow(() -> new RegistroNaoEncontradoException("Contato não encontrado."));
        contato.setConBlAtivo(false);
        this.contatoRepository.save(contato);
    }
}
