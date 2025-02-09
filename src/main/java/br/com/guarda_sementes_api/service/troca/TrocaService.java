package br.com.guarda_sementes_api.service.troca;

import br.com.guarda_sementes_api.service.troca.dto.TrocaDadosCompletosDto;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDto;
import br.com.guarda_sementes_api.service.troca.form.TrocaFiltroForm;
import br.com.guarda_sementes_api.service.troca.form.TrocaForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TrocaService {
    TrocaDto cadastrarOuAtualizarTroca(UUID troNrId, TrocaForm form);
    //Page<TrocaComStatusDto> listarTrocasDoUsuarioRemetente(TrocaFiltroForm filtro, Pageable pageable);
    //Page<TrocaComStatusDto> listarTrocasDoUsuarioDestinatario(TrocaFiltroForm filtro, Pageable pageable);
    Page<TrocaDadosCompletosDto> listarTrocasDoUsuario(TrocaFiltroForm filtro, Pageable pageable);
    TrocaDto obterTrocaPorId(UUID troNrId);
    void aceitarTroca(UUID troNrId);
    void recusarTroca(UUID troNrId);
    void cancelarTroca(UUID troNrId);
}
