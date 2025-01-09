package br.com.guarda_sementes_api.model.sementedisponiveltroca;

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
@Table(name = "sdt_semente_disponivel_troca")
public class SementeDisponivelTrocaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sdt_nr_id")
    private Long sdtNrId;

    @Column(name = "sdt_bl_disponivel")
    @Builder.Default
    private Boolean sdtBlDisponivel = Boolean.TRUE;

    @Column(name = "sdt_nr_quantidade")
    private Float sdtNrQuantidade;

    @Column(name = "sem_nr_id_semente")
    private Long semNrIdSemente;

    @Column(name = "sdt_tx_observacoes")
    private String sdtTxObservacoes;

    @CreationTimestamp
    @Column(name = "sdt_dt_created_at")
    private LocalDateTime sdtDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "sdt_dt_updated_at")
    private LocalDateTime sdtDtUpdatedAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        SementeDisponivelTrocaEntidade that = (SementeDisponivelTrocaEntidade) o;
        return getSdtNrId() != null && Objects.equals(getSdtNrId(), that.getSdtNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}

