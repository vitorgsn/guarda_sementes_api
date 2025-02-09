package br.com.guarda_sementes_api.model.armazem;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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

    @Column(name = "arm_tx_descricao")
    private String armTxDescricao;

    @CreationTimestamp
    @Column(name = "arm_dt_created_at")
    private LocalDateTime armDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "arm_dt_updated_at")
    private LocalDateTime armDtUpdateAt;

    @Column(name = "cta_nr_id")
    private Long ctaNrId;

    @Column(name = "usu_nr_id")
    private UUID usuNrId;

    @Column(name = "arm_bl_ativo")
    @Builder.Default
    private Boolean armBlAtivo = Boolean.TRUE;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ArmazemEntidade that = (ArmazemEntidade) o;
        return getArmNrId() != null && Objects.equals(getArmNrId(), that.getArmNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
