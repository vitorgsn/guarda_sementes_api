package br.com.guarda_sementes_api.model.estado;

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
@Table(name = "est_estado")
public class EstadoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "est_nr_id")
    private Long estNrId;

    @Column(name = "est_tx_sigla")
    private String estTxSigla;

    @Column(name = "est_tx_nome")
    private String estTxNome;

    @CreationTimestamp
    @Column(name = "est_dt_created_at")
    private LocalDateTime estDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "est_dt_updated_at")
    private LocalDateTime estDtUpdateAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EstadoEntidade that = (EstadoEntidade) o;
        return getEstNrId() != null && Objects.equals(getEstNrId(), that.getEstNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
