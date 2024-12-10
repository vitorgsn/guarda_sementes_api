package br.com.guarda_sementes_api.model.semente;

import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;
import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sem_semente")
public class SementeEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sem_nr_id")
    private Long semNrId;

    @Column(name = "sem_tx_nome", nullable = false, length = 200)
    private String semTxNome;

    @Column(name = "sem_nr_quantidade", nullable = false)
    private Float semNrQuantidade;

    @Column(name = "sem_tx_descricao", nullable = true, length = 256)
    private String semTxDescricao;

    @CreationTimestamp
    @Column(name = "sem_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime semDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "sem_dt_updated_at")
    private LocalDateTime semDtUpdateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arm_nr_id", nullable = false, referencedColumnName = "arm_nr_id")
    private ArmazemEntidade armNrId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tro_nr_id", referencedColumnName = "tro_nr_id")
    private TrocaEntidade troNrId;
}
