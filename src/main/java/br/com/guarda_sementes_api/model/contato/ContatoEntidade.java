package br.com.guarda_sementes_api.model.contato;

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
@Table(name = "con_contato")
public class ContatoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_nr_id")
    private Long conNrId;

    @Column(name = "con_tx_email")
    private String conTxEmail;

    @Column(name = "con_tx_numero")
    private String conTxNumero;

    @CreationTimestamp
    @Column(name = "con_dt_created_at")
    private LocalDateTime conDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "con_dt_updated_at")
    private LocalDateTime conDtUpdateAt;

    @Column(name = "con_bl_ativo")
    @Builder.Default
    private Boolean conBlAtivo = true;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ContatoEntidade that = (ContatoEntidade) o;
        return getConNrId() != null && Objects.equals(getConNrId(), that.getConNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
