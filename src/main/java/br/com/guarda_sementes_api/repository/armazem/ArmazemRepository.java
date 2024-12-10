package br.com.guarda_sementes_api.repository.armazem;

import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmazemRepository extends JpaRepository<ArmazemEntidade, Long> {
}
