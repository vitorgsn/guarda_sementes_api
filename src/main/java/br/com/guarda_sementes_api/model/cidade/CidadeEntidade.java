package br.com.guarda_sementes_api.model.cidade;

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
@Table(name = "cid_cidade")
public class CidadeEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_nr_id")
    private Long cidNrId;

    @Column(name = "cid_tx_nome")
    private String cidTxNome;

    @CreationTimestamp
    @Column(name = "cid_dt_created_at")
    private LocalDateTime cidDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "cid_dt_updated_at")
    private LocalDateTime cidDtUpdateAt;

    @Column(name = "est_nr_id")
    private Long estNrId;

    @Column(name = "cid_bl_ativo")
    @Builder.Default
    private Boolean cidBlAtivo = Boolean.TRUE;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        CidadeEntidade that = (CidadeEntidade) o;
        return getCidNrId() != null && Objects.equals(getCidNrId(), that.getCidNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
