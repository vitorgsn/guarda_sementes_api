package br.com.guarda_sementes_api.repository.endereco;

import br.com.guarda_sementes_api.model.endereco.EnderecoEntidade;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        end_endereco ende 
                    where 
                        ende.end_nr_id = :endNrId and ende.end_bl_ativo = true
                    """)
    Optional<EnderecoEntidade> buscarEnderecoPorId(long endNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                        ende.*
                    from
                        end_endereco ende
                    left join
                        enu_endereco_usuario enu on enu.end_nr_id = ende.end_nr_id
                    where
                        ende.end_bl_ativo = true
                    and enu.usu_nr_id =:usuNrId
                    and (:#{#filtro.endTxBairro() == null} or upper(ende.end_tx_bairro) like upper(concat('%', coalesce(:#{#filtro.endTxBairro()}, ''), '%')))
                    and (:#{#filtro.endTxLogradouro() == null} or upper(ende.end_tx_logradouro) like upper(concat('%', coalesce(:#{#filtro.endTxLogradouro()}, ''), '%')))                   
                    and (:#{#filtro.endTxNumero() == null} or upper(ende.end_tx_numero) like upper(concat('%', coalesce(:#{#filtro.endTxNumero()}, ''), '%')))
                    and (:#{#filtro.endTxReferencia() == null} or upper(ende.end_tx_referencia) like upper(concat('%', coalesce(:#{#filtro.endTxReferencia()}, ''), '%')))
                    and (:#{#filtro.cidNrId() == null} or ende.cid_nr_id=:#{#filtro.cidNrId()})
                    """)
    Page<EnderecoEntidade> listarEnderecosDoUsuario(@Param("filtro") EnderecoFiltroForm filtro, Pageable pageable, UUID usuNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                        ende.*
                    from
                        end_endereco ende
                    left join
                        enu_endereco_usuario enu on enu.end_nr_id = ende.end_nr_id
                    where
                        ende.end_bl_ativo = true
                    and enu.usu_nr_id =:usuNrId
                    and ende.end_bl_endereco_padrao = true
                    """)
    Optional<EnderecoEntidade> buscarEnderecoPadrao(UUID usuNrId);
}
