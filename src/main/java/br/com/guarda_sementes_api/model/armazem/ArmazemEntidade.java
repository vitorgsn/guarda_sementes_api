package br.com.guarda_sementes_api.model.armazem;

import br.com.guarda_sementes_api.model.semente.SementeEntidade;
import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "arm_armazem")
public class ArmazemEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "arm_nr_id")
    private Long armNrId;

    @Column(name = "arm_tx_descricao", nullable = true, length = 256)
    private String armTxDescricao;

    @CreationTimestamp
    @Column(name = "arm_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime armDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "arm_dt_updated_at")
    private LocalDateTime armDtUpdateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cta_nr_id", nullable = false, referencedColumnName = "cta_nr_id")
    private CategoriaArmazemEntidade ctaNrId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false, referencedColumnName = "usu_nr_id")
    private UsuarioEntidade usuNrId;

    @OneToMany(mappedBy = "armNrId", fetch = FetchType.LAZY)
    @Builder.Default
    private List<SementeEntidade> armListSemente = new ArrayList<>();

}
