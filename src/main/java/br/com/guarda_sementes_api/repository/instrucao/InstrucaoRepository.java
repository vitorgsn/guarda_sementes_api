package br.com.guarda_sementes_api.repository.instrucao;

import br.com.guarda_sementes_api.model.instrucao.InstrucaoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrucaoRepository extends JpaRepository<InstrucaoEntidade, Long> {
}
