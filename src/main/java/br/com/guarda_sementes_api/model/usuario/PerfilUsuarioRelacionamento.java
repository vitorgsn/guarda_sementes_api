package br.com.guarda_sementes_api.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pu_perfil_usuario")
public class PerfilUsuarioRelacionamento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pu_nr_id")
    private Long puNrId;
    @Column(name = "per_nr_id", nullable = false)
    private Long perNrId;
    @Column(name = "usu_nr_id", nullable = false)
    private UUID usuNrId;

    @ManyToOne()
    @MapsId("perNrId")
    @JoinColumn(name = "per_nr_id", referencedColumnName = "per_nr_id", nullable = false)
    private PerfilEntidade puObjPerfil;
    @ManyToOne()
    @MapsId("usuNrId")
    @JoinColumn(name = "usu_nr_id", referencedColumnName = "usu_nr_id", nullable = false)
    private UsuarioEntidade puObjUsuario;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PerfilUsuarioRelacionamento that = (PerfilUsuarioRelacionamento) o;
        return getPuNrId() != null && Objects.equals(getPuNrId(), that.getPuNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
