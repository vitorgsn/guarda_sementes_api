package br.com.guarda_sementes_api.repository.semente;

import br.com.guarda_sementes_api.model.semente.SementeEntidade;
import br.com.guarda_sementes_api.service.semente.form.SementeFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SementeRepository extends JpaRepository<SementeEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        sem_semente sem 
                    where 
                        sem.sem_nr_id = :semNrId and sem.sem_bl_ativo = true
                    """)
    Optional<SementeEntidade> buscarSementePorId(long semNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        sem.*
                    from 
                        sem_semente sem
                    left join
                        amu_armazem_usuario amu on amu.arm_nr_id = sem.arm_nr_id
                    where
                        sem.sem_bl_ativo = true
                    and amu.usu_nr_id =:usuNrId
                    and (:#{#filtro.semNrId() == null} or sem.sem_nr_id=:#{#filtro.semNrId()})
                    and (:#{#filtro.semNrQuantidade() == null} or sem.sem_nr_quantidade=:#{#filtro.semNrQuantidade()})
                    and (:#{#filtro.armNrId() == null} or sem.arm_nr_id=:#{#filtro.armNrId()})
                    and (
                        (:#{#filtro.semTxDescricao() == null} or upper(sem.sem_tx_descricao) like upper(concat('%', coalesce(:#{#filtro.semTxDescricao()}, ''), '%')))
                    or  (:#{#filtro.semTxNome() == null} or upper(sem.sem_tx_nome) like upper(concat('%', coalesce(:#{#filtro.semTxNome()}, ''), '%')))
                        )                                                    
                    """)
    Page<SementeEntidade> listarSementesDoUsuario(@Param("filtro") SementeFiltroForm filtro, Pageable pageable, UUID usuNrId);
}
