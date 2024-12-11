package br.com.guarda_sementes_api.repository.usuario;

import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntidade, UUID> {
    UserDetails findByUsuTxLogin(String usuTxLogin);
}
