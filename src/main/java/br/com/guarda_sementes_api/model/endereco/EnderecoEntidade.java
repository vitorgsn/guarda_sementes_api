package br.com.guarda_sementes_api.model.endereco;

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
@Table(name = "end_endereco")
public class EnderecoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_nr_id")
    private Long endNrId;

    @Column(name = "end_tx_bairro")
    private String endTxBairro;

    @Column(name = "end_tx_logradouro")
    private String endTxLogradouro;

    @Column(name = "end_tx_numero")
    private String endTxNumero;

    @Column(name = "end_tx_referencia")
    private String endTxReferencia;

    @CreationTimestamp
    @Column(name = "end_dt_created_at")
    private LocalDateTime endDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "end_dt_updated_at")
    private LocalDateTime endDtUpdateAt;

    @Column(name = "cid_nr_id")
    private Long cidNrId;

    @Column(name = "end_bl_ativo")
    @Builder.Default
    private Boolean endBlAtivo = Boolean.TRUE;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EnderecoEntidade that = (EnderecoEntidade) o;
        return getEndNrId() != null && Objects.equals(getEndNrId(), that.getEndNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
