package br.com.guarda_sementes_api.service.endereco.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.exceptions.RestricaoPorRegraDeNegocioException;
import br.com.guarda_sementes_api.model.endereco.EnderecoEntidade;
import br.com.guarda_sementes_api.repository.endereco.EnderecoRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.endereco.EnderecoService;
import br.com.guarda_sementes_api.service.endereco.dto.EnderecoDadosCompletosDto;
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

        var usuarioAutenticado = this.getUsuarioAutenticado();
        var enderecoPadrao = this.enderecoRepository.buscarEnderecoPadrao(usuarioAutenticado.getUsuNrId());

        if (!form.endBlEnderecoPadrao()) {
            if (enderecoPadrao.isEmpty()) {
                endereco.setEndBlEnderecoPadrao(true);
            } else {
                endereco.setEndBlEnderecoPadrao(false);
            }
        } else {
            if (enderecoPadrao.isPresent()) {
                enderecoPadrao.get().setEndBlEnderecoPadrao(false);
                this.enderecoRepository.save(enderecoPadrao.get());
            }

            endereco.setEndBlEnderecoPadrao(true);
        }

        this.enderecoRepository.save(endereco);

        this.enderecoUsuarioService.cadastrarOuAtualizarEnderecoUsuario(endereco.getEndNrId(), usuarioAutenticado.getUsuNrId());

        return new EnderecoDto(endereco);
    }

    @Override
    public Page<EnderecoDadosCompletosDto> listarEnderecosDoUsuario(EnderecoFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.enderecoRepository.listarEnderecosDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(EnderecoDadosCompletosDto::new);
    }

    @Override
    public EnderecoDto obterEnderecoPorId(Long endNrId) {
        var endereco = this.enderecoRepository.buscarEnderecoPorId(endNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado."));

        return new EnderecoDto(endereco);
    }

    @Override
    @Transactional
    public void deletarEndereco(Long endNrId) {
        var endereco = this.enderecoRepository.buscarEnderecoPorId(endNrId).orElseThrow(() -> new RegistroNaoEncontradoException("Endereço não encontrado."));
        var enderecos = this.enderecoRepository.listarEnderecos(this.getUsuarioAutenticado().getUsuNrId());

        if (enderecos.size() < 2) {
            throw new RestricaoPorRegraDeNegocioException("Não foi possível deletar o endereço, pois ele é seu único endereço.");
        }

        if (endereco.getEndBlEnderecoPadrao()) {
            if (!enderecos.isEmpty()) {
                var primeiroEndereco = enderecos.getFirst();
                primeiroEndereco.setEndBlEnderecoPadrao(true);
                this.enderecoRepository.save(primeiroEndereco);
            }
        }

        endereco.setEndBlEnderecoPadrao(false);
        endereco.setEndBlAtivo(false);
        this.enderecoRepository.save(endereco);
    }

    @Override
    public EnderecoDto buscarEnderecoPadrao() {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        var endereco = this.enderecoRepository.buscarEnderecoPadrao(usuarioAutenticado.getUsuNrId())
                .orElseThrow(() -> new RegistroNaoEncontradoException("Endereço padrão não encontrado."));

        return new EnderecoDto(endereco);
    }
}
