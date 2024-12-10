package br.com.guarda_sementes_api.model.usuario;

import jakarta.persistence.*;
import lombok.*;

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
}
