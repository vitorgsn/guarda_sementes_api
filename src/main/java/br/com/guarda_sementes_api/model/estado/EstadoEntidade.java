package br.com.guarda_sementes_api.model.estado;

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
@Table(name = "est_estado")
public class EstadoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_nr_id")
    private Long estNrId;

    @Column(name = "est_tx_sigla", nullable = false, length = 2)
    private String estTxSigla;

    @Column(name = "est_tx_nome", nullable = false, length = 100)
    private String estTxNome;

    @CreationTimestamp
    @Column(name = "est_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime estDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "est_dt_updated_at")
    private LocalDateTime estDtUpdateAt;
}
