package br.com.guarda_sementes_api.model.troca;

import br.com.guarda_sementes_api.model.troca.enuns.StatusTrocaEnum;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = "stt_status_troca")
public class StatusTrocaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stt_nr_id")
    private Long sttNrId;

    @Column(name = "stt_tx_status")
    @Enumerated(EnumType.STRING)
    private StatusTrocaEnum sttTxStatus;

    @Column(name = "tro_nr_id_troca")
    private UUID troNrIdTroca;

    @Column(name = "stt_dt_status_troca")
    private LocalDateTime sttDtStatusTroca;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StatusTrocaEntidade that = (StatusTrocaEntidade) o;
        return getSttNrId() != null && Objects.equals(getSttNrId(), that.getSttNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
