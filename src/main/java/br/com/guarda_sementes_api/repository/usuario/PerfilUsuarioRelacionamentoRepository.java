package br.com.guarda_sementes_api.repository.usuario;

import br.com.guarda_sementes_api.model.usuario.PerfilUsuarioRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilUsuarioRelacionamentoRepository extends JpaRepository<PerfilUsuarioRelacionamento, Long> {
}
