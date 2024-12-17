package br.com.guarda_sementes_api.repository.contato;

import br.com.guarda_sementes_api.model.contato.ContatoUsuarioRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContatoUsuarioRepository extends JpaRepository<ContatoUsuarioRelacionamento, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        cou_contato_usuario cou
                    where 
                        cou.usu_nr_id =:usuNrId and cou.con_nr_id =:conNrId
                    """)
    Optional<ContatoUsuarioRelacionamento> buscarContatoUsuarioPorUsuNrIdEConNrId(UUID usuNrId, Long conNrId);
}
