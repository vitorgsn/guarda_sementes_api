package br.com.guarda_sementes_api.repository.troca;

import br.com.guarda_sementes_api.model.troca.StatusTrocaEntidade;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StatusTrocaRepository extends JpaRepository<StatusTrocaEntidade, Long> {
    @Query(nativeQuery = true,
            value = """
                    select
                        stt.stt_tx_status
                    from
                        public.stt_status_troca stt
                    where
                        stt.tro_nr_id_troca = :troNrId
                        and stt.stt_dt_status_troca = (
                            select max(sub_stt.stt_dt_status_troca)
                            from public.stt_status_troca sub_stt
                            where sub_stt.tro_nr_id_troca = :troNrId
                        );
                    """)
    StatusTrocaEnum obterStatusAtualDaTroca(@Param("troNrId") UUID troNrId);
}
