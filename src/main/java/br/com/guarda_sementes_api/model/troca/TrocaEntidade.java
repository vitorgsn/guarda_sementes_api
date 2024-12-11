package br.com.guarda_sementes_api.model.troca;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTroca;
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
@Table(name = "tro_troca")
public class TrocaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tro_nr_id")
    private UUID troNrId;

    @Column(name = "tro_tx_instruncoes")
    private String troTxInstruncoes;

    @Enumerated(EnumType.STRING)
    @Column(name = "tro_tx_status_troca")
    @Builder.Default
    private StatusTroca troTxStatusTroca = StatusTroca.PENDENTE;

    @Column(name = "usu_nr_id_remetente")
    private UUID usuNrIdRemetente;

    @Column(name = "usu_nr_id_destinatario")
    private UUID usuNrIdDestinatario;

    @CreationTimestamp
    @Column(name = "tro_dt_created_at")
    private LocalDateTime troDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "tro_dt_updated_at")
    private LocalDateTime troDtUpdateAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TrocaEntidade that = (TrocaEntidade) o;
        return getTroNrId() != null && Objects.equals(getTroNrId(), that.getTroNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
