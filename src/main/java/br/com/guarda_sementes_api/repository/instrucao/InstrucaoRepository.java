package br.com.guarda_sementes_api.repository.instrucao;

import br.com.guarda_sementes_api.model.instrucao.InstrucaoEntidade;
import br.com.guarda_sementes_api.service.instrucao.form.InstrucaoFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstrucaoRepository extends JpaRepository<InstrucaoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        ins_instrucao ins
                    where 
                        ins.ins_nr_id = :insNrId and ins.ins_bl_ativo = true
                    """)
    Optional<InstrucaoEntidade> buscarInstrucaoPorId(long insNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        ins_instrucao ins
                    where 
                        ins.ins_bl_ativo = true
                    and (:#{#filtro.insNrId() == null} or ins.ins_nr_id=:#{#filtro.insNrId()})
                    and (:#{#filtro.insTxTitulo() == null} or upper(ins.ins_tx_titulo) like upper(concat('%', coalesce(:#{#filtro.insTxTitulo()}, ''), '%')))
                    and (:#{#filtro.insTxInstrucao() == null} or upper(ins.ins_tx_instrucao) like upper(concat('%', coalesce(:#{#filtro.insTxInstrucao()}, ''), '%')))                   
                    """)
    Page<InstrucaoEntidade> listarInstrucoes(@Param("filtro") InstrucaoFiltroForm filtro, Pageable pageable);
}
