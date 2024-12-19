package br.com.guarda_sementes_api.repository.armazem;

import br.com.guarda_sementes_api.model.armazem.CategoriaArmazemEntidade;
import br.com.guarda_sementes_api.service.armazem.form.CategoriaArmazemFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaArmazemRepository extends JpaRepository<CategoriaArmazemEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        cta_categoria_armazem cta 
                    where 
                        cta.cta_nr_id = :ctaNrId and cta.cta_bl_ativo = true
                    """)
    Optional<CategoriaArmazemEntidade> buscarCategoriaArmazemPorId(long ctaNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        cta_categoria_armazem cta 
                    where 
                        cta.cta_bl_ativo = true
                    and (:#{#filtro.ctaNrId() == null} or cta.cta_nr_id=:#{#filtro.ctaNrId()})
                    and (:#{#filtro.ctaTxNome() == null} or upper(cta.cta_tx_nome) like upper(concat('%', coalesce(:#{#filtro.ctaTxNome()}, ''), '%')))
                    and (:#{#filtro.ctaTxDescricao() == null} or upper(cta.cta_tx_descricao) like upper(concat('%', coalesce(:#{#filtro.ctaTxDescricao()}, ''), '%')))                   
                    """)
    Page<CategoriaArmazemEntidade> listarCategoriasArmazem(@Param("filtro") CategoriaArmazemFiltroForm filtro, Pageable pageable);
}
