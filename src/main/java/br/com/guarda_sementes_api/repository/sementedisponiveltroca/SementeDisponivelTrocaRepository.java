package br.com.guarda_sementes_api.repository.sementedisponiveltroca;

import br.com.guarda_sementes_api.model.sementedisponiveltroca.SementeDisponivelTrocaEntidade;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SementeDisponivelTrocaRepository extends JpaRepository<SementeDisponivelTrocaEntidade, Long> {
    @Query(nativeQuery = true,
            value = """
                    select 
                        sdt.* 
                    from 
                        sdt_semente_disponivel_troca sdt 
                    where 
                        sdt.sdt_nr_id = :sdtNrId and sdt.sdt_bl_disponivel = true
                    """)
    Optional<SementeDisponivelTrocaEntidade> buscarSementeDisponivelTrocaPorId(long sdtNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        sdt.* 
                    from 
                        sdt_semente_disponivel_troca sdt 
                    where 
                        sdt.sdt_bl_disponivel = true
                    and (:#{#filtro.sdtNrId() == null} or sdt.sdt_nr_id=:#{#filtro.sdtNrId()})
                    and (:#{#filtro.sdtNrQuantidade() == null} or sdt.sdt_nr_quantidade=:#{#filtro.sdtNrQuantidade()})
                    and (:#{#filtro.semNrIdSemente() == null} or sdt.sem_nr_id_semente=:#{#filtro.semNrIdSemente()})
                    and (:#{#filtro.sdtTxObservacoes() == null} or upper(sdt.sdt_tx_observacoes) like upper(concat('%', coalesce(:#{#filtro.sdtTxObservacoes()}, ''), '%')))
                    """)
    Page<SementeDisponivelTrocaEntidade> listarSementesDisponiveisParaTroca(@Param("filtro") SementeDisponivelTrocaFiltroForm filtro, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select 
                        sdt.* 
                    from 
                        sdt_semente_disponivel_troca sdt 
                    where 
                        sdt.sem_nr_id_semente = :semNrId and sdt.sdt_bl_disponivel = true
                    """)
    SementeDisponivelTrocaEntidade buscarSementeDisponivelTrocaPorSemNrId(long semNrId);
}
