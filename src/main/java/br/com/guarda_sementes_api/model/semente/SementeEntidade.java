package br.com.guarda_sementes_api.model.semente;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Column(name = "sem_tx_nome")
    private String semTxNome;

    @Column(name = "sem_nr_quantidade")
    private Float semNrQuantidade;

    @Column(name = "sem_tx_descricao")
    private String semTxDescricao;

    @CreationTimestamp
    @Column(name = "sem_dt_created_at")
    private LocalDateTime semDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "sem_dt_updated_at")
    private LocalDateTime semDtUpdateAt;

    @Column(name = "sem_bl_ativo")
    @Builder.Default
    private Boolean semBlAtivo = Boolean.TRUE;

    @Column(name = "arm_nr_id")
    private Long armNrId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SementeEntidade that = (SementeEntidade) o;
        return getSemNrId() != null && Objects.equals(getSemNrId(), that.getSemNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
