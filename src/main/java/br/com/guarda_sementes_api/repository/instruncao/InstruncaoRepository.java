package br.com.guarda_sementes_api.repository.instruncao;

import br.com.guarda_sementes_api.model.instruncao.InstruncaoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstruncaoRepository extends JpaRepository<InstruncaoEntidade, Long> {
}
