package br.com.guarda_sementes_api.repository.estado;

import br.com.guarda_sementes_api.model.estado.EstadoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntidade, Long> {
}
