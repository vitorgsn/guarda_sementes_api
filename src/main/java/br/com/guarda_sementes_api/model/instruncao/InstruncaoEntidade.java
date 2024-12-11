package br.com.guarda_sementes_api.model.instruncao;

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
@Table(name = "ins_instruncao")
public class InstruncaoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_nr_id")
    private Long insNrId;

    @Column(name = "ins_tx_titulo")
    private String insTxTitulo;

    @Column(name = "ins_tx_instruncao")
    private String insTxInstruncao;

    @CreationTimestamp
    @Column(name = "ins_dt_created_at")
    private LocalDateTime insDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "ins_dt_updated_at")
    private LocalDateTime insDtUpdateAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        InstruncaoEntidade that = (InstruncaoEntidade) o;
        return getInsNrId() != null && Objects.equals(getInsNrId(), that.getInsNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
