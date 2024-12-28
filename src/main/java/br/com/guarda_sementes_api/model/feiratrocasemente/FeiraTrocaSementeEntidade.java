package br.com.guarda_sementes_api.model.feiratrocasemente;

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
@Table(name = "fts_feira_troca_semente")
public class FeiraTrocaSementeEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fts_nr_id")
    private Long ftsNrId;

    @Column(name = "fts_bl_disponivel")
    @Builder.Default
    private Boolean ftsBlDisponivel = Boolean.TRUE;

    @Column(name = "fts_nr_quantidade")
    private Float ftsNrQuantidade;

    @Column(name = "sem_nr_id_semente")
    private Long semNrIdSemente;

    @CreationTimestamp
    @Column(name = "fts_dt_created_at")
    private LocalDateTime ftsDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "fts_dt_updated_at")
    private LocalDateTime ftsDtUpdatedAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        FeiraTrocaSementeEntidade that = (FeiraTrocaSementeEntidade) o;
        return getFtsNrId() != null && Objects.equals(getFtsNrId(), that.getFtsNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

