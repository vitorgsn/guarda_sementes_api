package br.com.guarda_sementes_api.service.endereco;

import br.com.guarda_sementes_api.service.endereco.dto.EnderecoDto;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoFiltroForm;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {
    EnderecoDto cadastrarOuAtualizarEndereco(Long endNrId, EnderecoForm form);
    Page<EnderecoDto> listarEnderecosDoUsuario(EnderecoFiltroForm filtro, Pageable pageable);
    EnderecoDto obterEnderecoPorId(Long endNrId);
    void deletarEndereco(Long endNrId);
}
