package br.com.guarda_sementes_api.model.armazem;

import br.com.guarda_sementes_api.model.endereco.EnderecoUsuarioRelacionamento;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "amu_armazem_usuario")
public class ArmazemUsuarioRelacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amu_nr_id")
    private Long amuNrId;

    @Column(name = "usu_nr_id")
    private UUID usuNrId;

    @Column(name = "arm_nr_id")
    private Long armNrId;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ArmazemUsuarioRelacionamento that = (ArmazemUsuarioRelacionamento) o;
        return getAmuNrId() != null && Objects.equals(getAmuNrId(), that.getAmuNrId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
