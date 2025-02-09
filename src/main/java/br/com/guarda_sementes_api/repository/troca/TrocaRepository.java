package br.com.guarda_sementes_api.repository.troca;

import br.com.guarda_sementes_api.model.semente.SementeEntidade;
import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import br.com.guarda_sementes_api.service.semente.form.SementeFiltroForm;
import br.com.guarda_sementes_api.service.troca.dto.TrocaComStatus;
import br.com.guarda_sementes_api.service.troca.dto.TrocaDadosCompletos;
import br.com.guarda_sementes_api.service.troca.form.TrocaFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrocaRepository extends JpaRepository<TrocaEntidade, Long> {
    @Query(nativeQuery = true,
            value = """
                    select 
                        tro.*
                    from 
                        tro_troca tro 
                    where 
                        tro.tro_nr_id = :troNrId
                    """)
    Optional<TrocaEntidade> buscarTrocaPorId(UUID troNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                    	tro.tro_nr_id as troNrId,
                    	tro.tro_tx_instruncoes as troTxInstruncoes,
                    	tro.usu_nr_id_remetente as usuNrIdRemetente,
                    	tro.usu_nr_id_destinatario as usuNrIdDestinatario,
                    	tro.tro_dt_created_at as troDtCreatedAt,
                    	stt.stt_tx_status as sttTxStatus,
                    	stt.stt_dt_status_troca as sttDtStatusTroca
                    from
                    	public.tro_troca tro
                    left join
                    	public.stt_status_troca stt on stt.tro_nr_id_troca = tro.tro_nr_id
                    where
                    	tro.usu_nr_id_remetente = :usuNrId
                    	and stt.stt_dt_status_troca = (
                            select max(sub_stt.stt_dt_status_troca)
                            from public.stt_status_troca sub_stt
                            where sub_stt.tro_nr_id_troca = tro.tro_nr_id
                        )    
                        and (:#{#filtro.troNrId() == null} or tro.tro_nr_id=:#{#filtro.troNrId()})
                        and (:#{#filtro.troTxInstruncoes() == null} or upper(tro.tro_tx_instruncoes) like upper(concat('%', coalesce(:#{#filtro.troTxInstruncoes()}, ''), '%')))
                    """)
    Page<TrocaComStatus> listarTrocasDoUsuarioRemetente(@Param("filtro") TrocaFiltroForm filtro, Pageable pageable, UUID usuNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                    	tro.tro_nr_id as troNrId,
                    	tro.tro_tx_instruncoes as troTxInstruncoes,
                    	tro.usu_nr_id_remetente as usuNrIdRemetente,
                    	tro.usu_nr_id_destinatario as usuNrIdDestinatario,
                    	tro.tro_dt_created_at as troDtCreatedAt,
                    	stt.stt_tx_status as sttTxStatus,
                    	stt.stt_dt_status_troca as sttDtStatusTroca
                    from
                    	public.tro_troca tro
                    left join
                    	public.stt_status_troca stt on stt.tro_nr_id_troca = tro.tro_nr_id
                    where
                    	tro.usu_nr_id_destinatario = :usuNrId
                    	and stt.stt_dt_status_troca = (
                            select max(sub_stt.stt_dt_status_troca)
                            from public.stt_status_troca sub_stt
                            where sub_stt.tro_nr_id_troca = tro.tro_nr_id
                        )    
                        and (:#{#filtro.troNrId() == null} or tro.tro_nr_id=:#{#filtro.troNrId()})
                        and (:#{#filtro.troTxInstruncoes() == null} or upper(tro.tro_tx_instruncoes) like upper(concat('%', coalesce(:#{#filtro.troTxInstruncoes()}, ''), '%')))
                    """)
    Page<TrocaComStatus> listarTrocasDoUsuarioDestinatario(@Param("filtro") TrocaFiltroForm filtro, Pageable pageable, UUID usuNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                    	tro.tro_nr_id as "troNrId",
                    	tro.tro_tx_instruncoes as "troTxInstrucoes",
                    	stt.stt_tx_status as "sttTxStatus",
                    	stt.stt_dt_status_troca as "sttDtStatusTroca",
                    	tro.tro_dt_created_at as "troDtCreatedAt",
                    	tro.usu_nr_id_remetente as "usuNrIdRemetente",
                    	remetente.usu_tx_nome as "usuTxNomeRemetente",
                    	tro.sem_nr_id_semente_remetente as "semNrIdSementeRemetente",
                    	semente_remetente.sem_tx_nome as "semTxNomeRemetente",
                    	tro.tro_nr_quantidade_semente_remetente as "troNrQuantidadeSementeRemetente",
                    	tro.usu_nr_id_destinatario as "usuNrIdDestinatario",
                    	destinatario.usu_tx_nome as "usuTxNomeDestinatario",
                    	tro.sem_nr_id_semente_destinatario as "semNrIdSementeDestinatario",
                    	semente_destinatario.sem_tx_nome as "semTxNomeDestinatario",
                    	tro.tro_nr_quantidade_semente_destinatario as "troNrQuantidadeSementeDestinatario"
                    from
                    	public.tro_troca tro
                    left join
                    	public.stt_status_troca stt on stt.tro_nr_id_troca = tro.tro_nr_id
                    left join
                    	public.usu_usuario remetente on remetente.usu_nr_id = tro.usu_nr_id_remetente
                    left join
                    	public.usu_usuario destinatario on destinatario.usu_nr_id = tro.usu_nr_id_destinatario
                   	left join
                   		public.sem_semente semente_remetente on semente_remetente.sem_nr_id = tro.sem_nr_id_semente_remetente
                   	left join
                   		public.sem_semente semente_destinatario on semente_destinatario.sem_nr_id = tro.sem_nr_id_semente_destinatario
                    where
                    	stt.stt_dt_status_troca = (
                            select max(sub_stt.stt_dt_status_troca)
                            from public.stt_status_troca sub_stt
                            where sub_stt.tro_nr_id_troca = tro.tro_nr_id
                        )
                        and (
                        	:usuNrId = tro.usu_nr_id_remetente
                        	or :usuNrId = tro.usu_nr_id_destinatario
                        )
                        and (:#{#filtro.troNrId() == null} or tro.tro_nr_id=:#{#filtro.troNrId()})
                        and (:#{#filtro.troTxInstruncoes() == null} or upper(tro.tro_tx_instruncoes) like upper(concat('%', coalesce(:#{#filtro.troTxInstruncoes()}, ''), '%')))
                        and (:#{#filtro.sttTxStatus() == null} or upper(stt.stt_tx_status) like upper(concat('%', coalesce(:#{#filtro.sttTxStatus()}, ''), '%')))
                    """)
    Page<TrocaDadosCompletos> listarTrocasDoUsuario(@Param("filtro") TrocaFiltroForm filtro, Pageable pageable, UUID usuNrId);
}
