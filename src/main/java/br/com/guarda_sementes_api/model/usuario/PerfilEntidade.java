package br.com.guarda_sementes_api.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "per_perfil")
public class PerfilEntidade implements GrantedAuthority {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "per_nr_id")
    private Long perNrId;

    @Column(name = "per_tx_nome")
    private String perTxNome;
    @Column(name = "per_tx_descricao")
    private String perTxDescricao;
    @Column(name = "per_tx_identificador")
    private String perTxIdentificador;
    @Column(name = "per_bl_ativo")
    private Boolean perBlAtivo;

    @CreationTimestamp
    @Column(name = "per_dt_created_at")
    private LocalDateTime perDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "per_dt_updated_at")
    private LocalDateTime perDtUpdateAt;

    @OneToMany(mappedBy = "puObjPerfil")
    @Builder.Default
    private List<PerfilUsuarioRelacionamento> perListPerfilUsuario = new ArrayList<>();

    @Override
    public String getAuthority() {
        return perTxIdentificador;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PerfilEntidade that = (PerfilEntidade) o;
        return getPerNrId() != null && Objects.equals(getPerNrId(), that.getPerNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
