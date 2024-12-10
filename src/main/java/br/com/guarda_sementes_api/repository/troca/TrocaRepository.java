package br.com.guarda_sementes_api.repository.troca;

import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrocaRepository extends JpaRepository<TrocaEntidade, Long> {
}
