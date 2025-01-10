package br.com.guarda_sementes_api.service.endereco.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.model.endereco.EnderecoEntidade;
import br.com.guarda_sementes_api.repository.endereco.EnderecoRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.endereco.EnderecoService;
import br.com.guarda_sementes_api.service.endereco.dto.EnderecoDto;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoFiltroForm;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl extends BaseService implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoUsuarioServiceImpl enderecoUsuarioService;

    @Override
    @Transactional
    public EnderecoDto cadastrarOuAtualizarEndereco(Long endNrId, EnderecoForm form) {

        var endereco = endNrId != null ?
                this.enderecoRepository.buscarEnderecoPorId(endNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado.")
                        ) : new EnderecoEntidade();

        endereco.setEndTxBairro(form.endTxBairro());
        endereco.setEndTxLogradouro(form.endTxLogradouro());
        endereco.setEndTxNumero(form.endTxNumero());
        endereco.setEndTxReferencia(form.endTxReferencia());
        endereco.setCidNrId(form.cidNrId());

        this.enderecoRepository.save(endereco);

        var usuarioAutenticado = this.getUsuarioAutenticado();

        this.enderecoUsuarioService.cadastrarOuAtualizarEnderecoUsuario(endereco.getEndNrId(), usuarioAutenticado.getUsuNrId());

        return new EnderecoDto(endereco);
    }

    @Override
    public Page<EnderecoDto> listarEnderecosDoUsuario(EnderecoFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.enderecoRepository.listarEnderecosDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(EnderecoDto::new);
    }

    @Override
    public EnderecoDto obterEnderecoPorId(Long endNrId) {
        var endereco = this.enderecoRepository.buscarEnderecoPorId(endNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado."));

        return new EnderecoDto(endereco);
    }

    @Override
    public void deletarEndereco(Long endNrId) {
        var endereco = this.enderecoRepository.buscarEnderecoPorId(endNrId).orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado."));
        endereco.setEndBlAtivo(false);
        this.enderecoRepository.save(endereco);
    }
}
