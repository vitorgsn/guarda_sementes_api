package br.com.guarda_sementes_api.service.armazem.impl;

import br.com.guarda_sementes_api.model.armazem.CategoriaArmazemEntidade;
import br.com.guarda_sementes_api.repository.armazem.CategoriaArmazemRepository;
import br.com.guarda_sementes_api.service.armazem.CategoriaArmazemService;
import br.com.guarda_sementes_api.service.armazem.dto.CategoriaArmazemDto;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemFiltroForm;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaArmazemServiceImpl implements CategoriaArmazemService {

    private final CategoriaArmazemRepository categoriaArmazemRepository;

    @Override
    public CategoriaArmazemDto cadastrarOuAtualizarCategoriaArmazem(Long ctaNrId, CategoriaArmazemForm form) {
        var categoria = ctaNrId != null ?
                this.categoriaArmazemRepository.buscarCategoriaArmazemPorId(ctaNrId)
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada")
                        ) : new CategoriaArmazemEntidade();

        categoria.setCtaTxNome(form.ctaTxNome());
        categoria.setCtaTxDescricao(form.ctaTxDescricao());

        this.categoriaArmazemRepository.save(categoria);

        return new CategoriaArmazemDto(categoria);
    }

    @Override
    public Page<CategoriaArmazemDto> listarCategoriasArmazem(CategoriaArmazemFiltroForm filtro, Pageable pageable) {
        return this.categoriaArmazemRepository.listarCategoriasArmazem(filtro, pageable).map(CategoriaArmazemDto::new);
    }

    @Override
    public CategoriaArmazemDto obterCategoriaArmazemPorId(Long ctaNrId) {

        var categoria = this.categoriaArmazemRepository.buscarCategoriaArmazemPorId(ctaNrId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return new CategoriaArmazemDto(categoria);
    }

    @Override
    public void deletarCategoriaArmazem(Long ctaNrId) {
        var categoria = this.categoriaArmazemRepository.buscarCategoriaArmazemPorId(ctaNrId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        categoria.setCtaBlAtivo(false);
        this.categoriaArmazemRepository.save(categoria);
    }
}
