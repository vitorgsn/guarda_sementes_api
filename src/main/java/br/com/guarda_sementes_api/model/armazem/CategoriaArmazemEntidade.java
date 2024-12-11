package br.com.guarda_sementes_api.model.armazem;

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
@Table(name = "cta_categoria_armazem")
public class CategoriaArmazemEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cta_nr_id")
    private Long ctaNrId;

    @Column(name = "cta_tx_nome")
    private String ctaTxNome;

    @Column(name = "cta_tx_descricao")
    private String ctaTxDescricao;

    @CreationTimestamp
    @Column(name = "cta_dt_created_at")
    private LocalDateTime ctaDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "cta_dt_updated_at")
    private LocalDateTime ctaDtUpdateAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CategoriaArmazemEntidade that = (CategoriaArmazemEntidade) o;
        return getCtaNrId() != null && Objects.equals(getCtaNrId(), that.getCtaNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
