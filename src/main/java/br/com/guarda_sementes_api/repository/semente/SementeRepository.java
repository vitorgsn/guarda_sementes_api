package br.com.guarda_sementes_api.repository.semente;

import br.com.guarda_sementes_api.model.semente.SementeEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SementeRepository extends JpaRepository<SementeEntidade, Long> {
}
