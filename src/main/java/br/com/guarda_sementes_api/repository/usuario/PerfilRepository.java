package br.com.guarda_sementes_api.repository.usuario;

import br.com.guarda_sementes_api.model.usuario.PerfilEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntidade, Long> {
}
