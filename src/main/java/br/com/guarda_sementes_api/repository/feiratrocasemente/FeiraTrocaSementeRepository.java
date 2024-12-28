package br.com.guarda_sementes_api.repository.feiratrocasemente;

import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;
import br.com.guarda_sementes_api.model.feiratrocasemente.FeiraTrocaSementeEntidade;
import br.com.guarda_sementes_api.service.armazem.form.ArmazemFiltroForm;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeFiltroForm;
import br.com.guarda_sementes_api.service.feiratrocasemente.form.FeiraTrocaSementeForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeiraTrocaSementeRepository extends JpaRepository<FeiraTrocaSementeEntidade, Long> {
    @Query(nativeQuery = true,
            value = """
                    select 
                        fts.* 
                    from 
                        fts_feira_troca_semente fts 
                    where 
                        fts.fts_nr_id = :ftsNrId and fts.fts_bl_disponivel = true
                    """)
    Optional<FeiraTrocaSementeEntidade> buscarFeiraTrocaSementePorId(long ftsNrId);

    @Query(nativeQuery = true,
            value = """
                    select 
                        fts.* 
                    from 
                        fts_feira_troca_semente fts
                    where 
                        fts.fts_bl_disponivel = true
                    and (:#{#filtro.ftsNrId() == null} or fts.fts_nr_id=:#{#filtro.ftsNrId()})
                    and (:#{#filtro.ftsNrQuantidade() == null} or fts.fts_nr_quantidade=:#{#filtro.ftsNrQuantidade()})
                    and (:#{#filtro.semNrIdSemente() == null} or fts.sem_nr_id_semente=:#{#filtro.semNrIdSemente()})
                    """)
    Page<FeiraTrocaSementeEntidade> listarSementesDisponiveisParaTroca(@Param("filtro") FeiraTrocaSementeFiltroForm filtro, Pageable pageable);
}
