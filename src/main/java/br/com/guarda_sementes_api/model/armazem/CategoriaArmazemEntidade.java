package br.com.guarda_sementes_api.model.armazem;

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
@Table(name = "cta_categoria_armazem")
public class CategoriaArmazemEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cta_nr_id")
    private Long ctaNrId;

    @Column(name = "cta_tx_nome", nullable = false, length = 100)
    private String ctaTxNome;

    @Column(name = "cta_tx_descricao", nullable = true, length = 256)
    private String ctaTxDescricao;

    @CreationTimestamp
    @Column(name = "cta_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime ctaDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "cta_dt_updated_at")
    private LocalDateTime ctaDtUpdateAt;

}
