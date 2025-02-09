package br.com.guarda_sementes_api.repository.armazem;

import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArmazemRepository extends JpaRepository<ArmazemEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        arm_armazem arm 
                    where 
                        arm.arm_nr_id = :armNrId and arm.arm_bl_ativo = true
                    """)
    Optional<ArmazemEntidade> buscarArmazemPorId(long armNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        arm.* 
                    from 
                        arm_armazem arm
                    where 
                        arm.arm_bl_ativo = true
                    and arm.usu_nr_id =:usuNrId
                    and (:#{#filtro.armNrId() == null} or arm.arm_nr_id=:#{#filtro.armNrId()})
                    and (:#{#filtro.armTxDescricao() == null} or upper(arm.arm_tx_descricao) like upper(concat('%', coalesce(:#{#filtro.armTxDescricao()}, ''), '%')))
                    and (:#{#filtro.ctaNrId() == null} or arm.cta_nr_id=:#{#filtro.ctaNrId()})
                    """)
    Page<ArmazemEntidade> listarArmazensDoUsuario(@Param("filtro") ArmazemFiltroForm filtro, Pageable pageable, UUID usuNrId);
}