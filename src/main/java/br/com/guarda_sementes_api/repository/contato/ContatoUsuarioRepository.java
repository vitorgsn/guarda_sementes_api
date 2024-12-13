package br.com.guarda_sementes_api.repository.contato;

import br.com.guarda_sementes_api.model.contato.ContatoUsuarioRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoUsuarioRepository extends JpaRepository<ContatoUsuarioRelacionamento, Long> {
}
