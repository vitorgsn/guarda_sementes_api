package br.com.guarda_sementes_api.repository.endereco;

import br.com.guarda_sementes_api.model.endereco.EnderecoUsuarioRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuarioRelacionamento, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        enu_endereco_usuario enu
                    where 
                        enu.usu_nr_id =:usuNrId and enu.end_nr_id =:endNrId
                    """)
    Optional<EnderecoUsuarioRelacionamento> buscarEnderecoUsuarioPorUsuNrIdEEndNrId(UUID usuNrId, Long endNrId);
}
