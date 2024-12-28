package br.com.guarda_sementes_api.repository.troca;

import br.com.guarda_sementes_api.model.troca.TrocaSementeRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrocaSementeRepository extends JpaRepository<TrocaSementeRelacionamento, Long> {
    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        trs_troca_semente trs
                    where 
                        trs.tro_nr_id =:troNrId and trs.sem_nr_id =:semNrId
                    """)
    Optional<TrocaSementeRelacionamento> buscarTrocaSementePorTroNrIdESemNrId(UUID troNrId, Long semNrId);
}
