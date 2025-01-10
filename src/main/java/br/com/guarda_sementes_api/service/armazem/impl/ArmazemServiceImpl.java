package br.com.guarda_sementes_api.service.armazem.impl;

import br.com.guarda_sementes_api.exceptions.RegistroNaoEncontradoException;
import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;
import br.com.guarda_sementes_api.repository.armazem.ArmazemRepository;
import br.com.guarda_sementes_api.service.BaseService;
import br.com.guarda_sementes_api.service.armazem.ArmazemService;
import br.com.guarda_sementes_api.service.armazem.ArmazemUsuarioService;
import br.com.guarda_sementes_api.service.armazem.CategoriaArmazemService;
import br.com.guarda_sementes_api.service.armazem.dto.ArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArmazemServiceImpl extends BaseService implements ArmazemService {
    private final ArmazemRepository armazemRepository;
    private final ArmazemUsuarioService armazemUsuarioService;
    private final CategoriaArmazemService categoriaArmazemService;

    @Override
    public ArmazemDto cadastrarOuAtualizarArmazem(Long armNrId, ArmazemForm form) {
        var armazem = armNrId != null ?
                this.armazemRepository.buscarArmazemPorId(armNrId)
                        .orElseThrow(() -> new RegistroNaoEncontradoException("Armazem não encontrado")
                        ) : new ArmazemEntidade();

        if (form.ctaNrId() != null) this.categoriaArmazemService.obterCategoriaArmazemPorId(form.ctaNrId());

        armazem.setArmTxDescricao(form.armTxDescricao());
        armazem.setCtaNrId(form.ctaNrId());

        this.armazemRepository.save(armazem);

        var usuarioAutenticado = this.getUsuarioAutenticado();

        this.armazemUsuarioService.cadastrarOuAtualizarArmazemUsuario(armazem.getArmNrId(), usuarioAutenticado.getUsuNrId());

        return new ArmazemDto(armazem);
    }

    @Override
    public Page<ArmazemDto> listarArmazensDoUsuario(ArmazemFiltroForm filtro, Pageable pageable) {
        var usuarioAutenticado = this.getUsuarioAutenticado();
        return this.armazemRepository.listarArmazensDoUsuario(filtro, pageable, usuarioAutenticado.getUsuNrId()).map(ArmazemDto::new);
    }

    @Override
    public ArmazemDto obterArmazemPorId(Long armNrId) {
        var armazem = this.armazemRepository.buscarArmazemPorId(armNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Armazem não encontrado"));
        return new ArmazemDto(armazem);
    }

    @Override
    public void deletarArmazem(Long armNrId) {
        var armazem = this.armazemRepository.buscarArmazemPorId(armNrId)
                .orElseThrow(() -> new RegistroNaoEncontradoException("Armazem não encontrado"));
        armazem.setArmBlAtivo(false);
        this.armazemRepository.save(armazem);
    }

    @Override
    public ArmazemDto criarArmazemParaUsuario(ArmazemForm form, UUID usuNrId) {
        var novoArmazem = new ArmazemEntidade();

        novoArmazem.setArmTxDescricao(form.armTxDescricao());
        novoArmazem.setCtaNrId(form.ctaNrId());

        this.armazemRepository.save(novoArmazem);

        this.armazemUsuarioService.cadastrarOuAtualizarArmazemUsuario(novoArmazem.getArmNrId(), usuNrId);

        return new ArmazemDto(novoArmazem);
    }
}
