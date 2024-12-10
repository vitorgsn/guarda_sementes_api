package br.com.guarda_sementes_api.repository.cidade;

import br.com.guarda_sementes_api.model.cidade.CidadeEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long> {
}
