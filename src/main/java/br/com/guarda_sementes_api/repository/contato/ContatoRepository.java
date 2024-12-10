package br.com.guarda_sementes_api.repository.contato;

import br.com.guarda_sementes_api.model.contato.ContatoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntidade, Long> {
}
