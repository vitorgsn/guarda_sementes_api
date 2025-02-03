package br.com.guarda_sementes_api.repository.cidade;

import br.com.guarda_sementes_api.model.cidade.CidadeEntidade;
import br.com.guarda_sementes_api.model.estado.EstadoEntidade;
import br.com.guarda_sementes_api.service.cidade.dto.CidadeDadosCompletos;
import br.com.guarda_sementes_api.service.cidade.form.CidadeFiltroForm;
import br.com.guarda_sementes_api.service.estado.form.EstadoFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        cid_cidade cid
                    where 
                        cid.cid_nr_id = :cidNrID and cid.cid_bl_ativo = true
                    """)
    Optional<CidadeEntidade> buscarCidadePorId(Long cidNrID);

    @Query(nativeQuery = true,
            value = """
                    select
                        cid.cid_nr_id as "cidNrId",
                        cid.cid_tx_nome as "cidTxNome",
                        est.est_nr_id as "estNrId",
                        est.est_tx_nome as "estTxNome",
                        est.est_tx_sigla as "estTxSigla"
                    from
                        cid_cidade cid
                    inner join
                    	est_estado est on est.est_nr_id = cid.est_nr_id
                    where
                        cid.cid_bl_ativo = true
                    and (:#{#filtro.cidNrId() == null} or cid.cid_nr_id=:#{#filtro.cidNrId()})
                    and (:#{#filtro.cidTxNome() == null} or upper(cid.cid_tx_nome) like upper(concat('%', coalesce(:#{#filtro.cidTxNome()}, ''), '%')))
                    and (:#{#filtro.estNrId() == null} or cid.est_nr_id=:#{#filtro.estNrId()})
                    """)
    Page<CidadeDadosCompletos> listarCidades(@Param("filtro") CidadeFiltroForm filtro, Pageable pageable);
}
