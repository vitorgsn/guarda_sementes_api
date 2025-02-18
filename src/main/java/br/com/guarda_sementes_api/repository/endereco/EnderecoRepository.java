package br.com.guarda_sementes_api.repository.endereco;

import br.com.guarda_sementes_api.model.endereco.EnderecoEntidade;
import br.com.guarda_sementes_api.service.endereco.dto.EnderecoDadosCompletos;
import br.com.guarda_sementes_api.service.endereco.form.EnderecoFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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
                    	ende.end_nr_id as "endNrId",
                    	ende.end_tx_bairro as "endTxBairro",
                    	ende.end_tx_logradouro as "endTxLogradouro",
                    	ende.end_tx_numero as "endTxNumero",
                    	ende.end_tx_referencia as "endTxReferencia",
                    	ende.end_bl_endereco_padrao as "endBlEnderecoPadrao",
                    	cid.cid_nr_id as "cidNrId",
                    	cid.cid_tx_nome as "cidTxNome",
                    	est.est_nr_id as "estNrId",
                    	est.est_tx_nome as "estTxNome",
                    	est.est_tx_sigla as "estTxSigla"
                    from
                        end_endereco ende
                    left join
                        enu_endereco_usuario enu on enu.end_nr_id = ende.end_nr_id
                    left join
                    	cid_cidade cid on cid.cid_nr_id = ende.cid_nr_id
                    left join
                    	est_estado est on est.est_nr_id = cid.est_nr_id
                    where
                        ende.end_bl_ativo = true
                    and enu.usu_nr_id =:usuNrId
                    and (:#{#filtro.endNrId() == null} or ende.end_nr_id=:#{#filtro.endNrId()})
                    and (:#{#filtro.endTxBairro() == null} or upper(ende.end_tx_bairro) like upper(concat('%', coalesce(:#{#filtro.endTxBairro()}, ''), '%')))
                    and (:#{#filtro.endTxLogradouro() == null} or upper(ende.end_tx_logradouro) like upper(concat('%', coalesce(:#{#filtro.endTxLogradouro()}, ''), '%')))                   
                    and (:#{#filtro.endTxNumero() == null} or upper(ende.end_tx_numero) like upper(concat('%', coalesce(:#{#filtro.endTxNumero()}, ''), '%')))
                    and (:#{#filtro.endTxReferencia() == null} or upper(ende.end_tx_referencia) like upper(concat('%', coalesce(:#{#filtro.endTxReferencia()}, ''), '%')))
                    and (:#{#filtro.cidNrId() == null} or cid.cid_nr_id=:#{#filtro.cidNrId()})
                    and (:#{#filtro.cidTxNome() == null} or upper(cid.cid_tx_nome) like upper(concat('%', coalesce(:#{#filtro.cidTxNome()}, ''), '%')))
                    and (:#{#filtro.estNrId() == null} or est.est_nr_id=:#{#filtro.estNrId()})
                    and (:#{#filtro.estTxNome() == null} or upper(est.est_tx_nome) like upper(concat('%', coalesce(:#{#filtro.estTxNome()}, ''), '%')))
                    and (:#{#filtro.estTxSigla() == null} or upper(est.est_tx_sigla) like upper(concat('%', coalesce(:#{#filtro.estTxSigla()}, ''), '%')))
                    """)
    Page<EnderecoDadosCompletos> listarEnderecosDoUsuario(@Param("filtro") EnderecoFiltroForm filtro, Pageable pageable, UUID usuNrId);

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
                    """)
    List<EnderecoEntidade> listarEnderecos(UUID usuNrId);
}
