package br.com.guarda_sementes_api.service.cidade.impl;

import br.com.guarda_sementes_api.model.cidade.CidadeEntidade;
import br.com.guarda_sementes_api.model.estado.EstadoEntidade;
import br.com.guarda_sementes_api.repository.cidade.CidadeRepository;
import br.com.guarda_sementes_api.service.cidade.CidadeService;
import br.com.guarda_sementes_api.service.cidade.dto.CidadeDto;
import br.com.guarda_sementes_api.service.cidade.form.CidadeFiltroForm;
import br.com.guarda_sementes_api.service.cidade.form.CidadeForm;
import br.com.guarda_sementes_api.service.estado.dto.EstadoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CidadeServiceImpl implements CidadeService {
    private final CidadeRepository cidadeRepository;


    @Override
    public CidadeDto cadastrarOuAtualizarCidade(Long cidNrID, CidadeForm form) {

        var cidade = cidNrID != null ?
                this.cidadeRepository.buscarCidadePorId(cidNrID)
                        .orElseThrow(() -> new RuntimeException("Cidade não encontrada")
                        ) : new CidadeEntidade();

        cidade.setCidTxNome(form.cidTxNome());
        cidade.setEstNrId(form.estNrId());

        this.cidadeRepository.save(cidade);

        return new CidadeDto(cidade);
    }

    @Override
    public Page<CidadeDto> listarCidades(CidadeFiltroForm filtro, Pageable pageable) {
        return this.cidadeRepository.listarCidades(filtro, pageable).map(CidadeDto::new);
    }

    @Override
    public CidadeDto obterCidadePorId(Long cidNrID) {
        var cidade = this.cidadeRepository.buscarCidadePorId(cidNrID)
                        .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return new CidadeDto(cidade);
    }

    @Override
    public void deletarCidade(Long cidNrID) {
        var cidade = this.cidadeRepository.buscarCidadePorId(cidNrID)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        this.cidadeRepository.delete(cidade);
    }
}
