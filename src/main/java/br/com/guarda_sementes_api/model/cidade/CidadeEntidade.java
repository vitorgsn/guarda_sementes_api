package br.com.guarda_sementes_api.model.cidade;

import br.com.guarda_sementes_api.model.estado.EstadoEntidade;
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
@Table(name = "cid_cidade")
public class CidadeEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_nr_id")
    private Long cidNrId;

    @Column(name = "cid_tx_nome", nullable = false, length = 100)
    private String cidTxNome;

    @CreationTimestamp
    @Column(name = "cid_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime cidDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "cid_dt_updated_at")
    private LocalDateTime cidDtUpdateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "est_nr_id", nullable = false, referencedColumnName = "est_nr_id")
    private EstadoEntidade estNrId;
}
