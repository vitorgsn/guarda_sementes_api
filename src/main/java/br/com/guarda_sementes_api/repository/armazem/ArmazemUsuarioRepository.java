package br.com.guarda_sementes_api.repository.armazem;

import br.com.guarda_sementes_api.model.armazem.ArmazemUsuarioRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArmazemUsuarioRepository extends JpaRepository<ArmazemUsuarioRelacionamento, Long> {
    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        amu_armazem_usuario amu
                    where 
                        amu.usu_nr_id =:usuNrId and amu.arm_nr_id =:armNrId
                    """)
    Optional<ArmazemUsuarioRelacionamento> buscarArmazemUsuarioPorUsuNrIdEArmNrId(UUID usuNrId, Long armNrId);
}
