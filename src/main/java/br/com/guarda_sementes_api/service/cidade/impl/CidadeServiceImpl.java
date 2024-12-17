package br.com.guarda_sementes_api.service.cidade.impl;

import br.com.guarda_sementes_api.repository.cidade.CidadeRepository;
import br.com.guarda_sementes_api.service.cidade.CidadeService;
import br.com.guarda_sementes_api.service.cidade.dto.CidadeDto;
import br.com.guarda_sementes_api.service.cidade.form.CidadeFiltroForm;
import br.com.guarda_sementes_api.service.cidade.form.CidadeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeServiceImpl implements CidadeService {
    private final CidadeRepository cidadeRepository;


    @Override
    public CidadeDto cadastrarOuAtualizarCidade(Long cidNrID, CidadeForm cidadeForm) {
        return null;
    }

    @Override
    public Page<CidadeDto> listarCidades(CidadeFiltroForm filtro, Pageable pageable) {
        return null;
    }

    @Override
    public CidadeDto obterCidadePorId(Long cidNrID) {
        return null;
    }

    @Override
    public void deletarCidade(Long cidNrID) {

    }
}
