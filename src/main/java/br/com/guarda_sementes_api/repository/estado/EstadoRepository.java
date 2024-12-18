package br.com.guarda_sementes_api.repository.estado;

import br.com.guarda_sementes_api.model.estado.EstadoEntidade;
import br.com.guarda_sementes_api.service.estado.form.EstadoFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        est_estado est 
                    where 
                        est.est_nr_id = :estNrId and est.est_bl_ativo = true
                    """)
    Optional<EstadoEntidade> buscarEstadoPorId(long estNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        est_estado est 
                    where 
                        est.est_bl_ativo = true
                    and (:#{#filtro.estNrId() == null} or est.est_nr_id=:#{#filtro.estNrId()})
                    and (:#{#filtro.estTxNome() == null} or upper(est.est_tx_nome) like upper(concat('%', coalesce(:#{#filtro.estTxNome()}, ''), '%')))
                    and (:#{#filtro.estTxSigla() == null} or upper(est.est_tx_sigla) like upper(concat('%', coalesce(:#{#filtro.estTxSigla()}, ''), '%')))                   
                    """)
    Page<EstadoEntidade> listarEstados(@Param("filtro") EstadoFiltroForm filtro, Pageable pageable);
}
