package br.com.guarda_sementes_api.repository.sementedisponiveltroca;

import br.com.guarda_sementes_api.model.sementedisponiveltroca.SementeDisponivelTrocaEntidade;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.dto.SementeDisponivelTrocaDadosCompletos;
import br.com.guarda_sementes_api.service.sementedisponiveltroca.form.SementeDisponivelTrocaFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

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
                        sdt.sdt_nr_id as "sdtNrId",
                        sdt.sdt_bl_disponivel as "sdtBlDisponivel",
                        sdt.sdt_nr_quantidade as "sdtNrQuantidade",
                        sdt.sdt_tx_observacoes as "sdtTxObservacoes",
                        sem.sem_nr_id as "semNrIdSemente",
                        sem.sem_tx_nome as "semTxNome",
                        usu.usu_nr_id as "usuNrId",
                        cid.cid_nr_id as "cidNrId",
                        cid.cid_tx_nome as "cidTxNome",
                        est.est_nr_id as "estNrId",
                        est.est_tx_nome as "estTxNome",
                        est.est_tx_sigla as "estTxSigla",
                        sdt.sdt_dt_created_at as "sdtDtCreatedAt",
                        sdt.sdt_dt_updated_at as "sdtDtUpdatedAt"
                    from
                        sdt_semente_disponivel_troca sdt
                    inner join
                        sem_semente sem on sdt.sem_nr_id_semente = sem.sem_nr_id
                    inner join
                        arm_armazem arm on arm.arm_nr_id = sem.arm_nr_id
                    inner join
                        usu_usuario usu on usu.usu_nr_id = arm.usu_nr_id
                    inner join
                        enu_endereco_usuario enu on enu.usu_nr_id = usu.usu_nr_id
                    inner join
                        end_endereco en on en.end_nr_id = enu.end_nr_id
                    inner join
                        cid_cidade cid on cid.cid_nr_id = en.cid_nr_id
                    inner join
                        est_estado est on est.est_nr_id = cid.est_nr_id
                    where
                        en.end_bl_endereco_padrao = true
                    and sdt.sdt_bl_disponivel = true
                    and sem.sem_bl_ativo = true
                    and usu.usu_nr_id != :usuNrId
                    and (:#{#filtro.sdtNrId() == null} or sdt.sdt_nr_id=:#{#filtro.sdtNrId()})
                    and (:#{#filtro.sdtNrQuantidade() == null} or sdt.sdt_nr_quantidade=:#{#filtro.sdtNrQuantidade()})
                    and (:#{#filtro.semNrIdSemente() == null} or sdt.sem_nr_id_semente=:#{#filtro.semNrIdSemente()})
                    and (:#{#filtro.cidNrId() == null} or cid.cid_nr_id=:#{#filtro.cidNrId()})
                    and (:#{#filtro.estNrId() == null} or est.est_nr_id=:#{#filtro.estNrId()})
                    and (:#{#filtro.estTxNome() == null} or upper(est.est_tx_nome) like upper(concat('%', coalesce(:#{#filtro.estTxNome()}, ''), '%')))
                    and (:#{#filtro.estTxSigla() == null} or upper(est.est_tx_sigla) like upper(concat('%', coalesce(:#{#filtro.estTxSigla()}, ''), '%')))
                    and (
                            (:#{#filtro.sdtTxObservacoes() == null} or upper(sdt.sdt_tx_observacoes) like upper(concat('%', coalesce(:#{#filtro.sdtTxObservacoes()}, ''), '%')))
                         or (:#{#filtro.semTxNome() == null} or upper(sem.sem_tx_nome) like upper(concat('%', coalesce(:#{#filtro.semTxNome()}, ''), '%')))   
                         or (:#{#filtro.cidTxNome() == null} or upper(cid.cid_tx_nome) like upper(concat('%', coalesce(:#{#filtro.cidTxNome()}, ''), '%'))) 
                        )
                    """)
    Page<SementeDisponivelTrocaDadosCompletos> listarSementesDisponiveisParaTroca(@Param("filtro") SementeDisponivelTrocaFiltroForm filtro, Pageable pageable, UUID usuNrId);

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
