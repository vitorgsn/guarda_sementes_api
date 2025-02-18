package br.com.guarda_sementes_api.repository.contato;

import br.com.guarda_sementes_api.model.contato.ContatoEntidade;
import br.com.guarda_sementes_api.service.contato.form.ContatoFiltroForm;
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
public interface ContatoRepository extends JpaRepository<ContatoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select 
                        * 
                    from 
                        con_contato con 
                    where 
                        con.con_nr_id = :conNrId and con.con_bl_ativo = true
                    """)
    Optional<ContatoEntidade> buscarContatoPorId(long conNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                        con.*
                    from
                        con_contato con
                    left join
                        cou_contato_usuario cou on cou.con_nr_id = con.con_nr_id
                    where
                        con.con_bl_ativo = true
                    and cou.usu_nr_id =:usuNrId
                    and (:#{#filtro.conTxEmail() == null} or upper(con.con_tx_email) like upper(concat('%', coalesce(:#{#filtro.conTxEmail()}, ''), '%')))
                    and (:#{#filtro.conTxNumero() == null} or upper(con.con_tx_numero) like upper(concat('%', coalesce(:#{#filtro.conTxNumero()}, ''), '%')))
                    """)
    Page<ContatoEntidade> listarContatosDoUsuario(@Param("filtro")ContatoFiltroForm filtro, Pageable pageable, UUID usuNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                        con.*
                    from
                        con_contato con
                    left join
                        cou_contato_usuario cou on cou.con_nr_id = con.con_nr_id
                    where
                        con.con_bl_ativo = true
                    and cou.usu_nr_id =:usuNrId
                    and con.con_bl_contato_padrao = true
                    """)
    Optional<ContatoEntidade> buscarContatoPadrao(UUID usuNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                        con.*
                    from
                        con_contato con
                    left join
                        cou_contato_usuario cou on cou.con_nr_id = con.con_nr_id
                    where
                        con.con_bl_ativo = true
                    and cou.usu_nr_id =:usuNrId
                    """)
    List<ContatoEntidade> listarContatos(UUID usuNrId);
}
