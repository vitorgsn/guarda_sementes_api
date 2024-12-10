package br.com.guarda_sementes_api.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "per_tx_nome", nullable = false, unique = true)
    private String perTxNome;
    @Column(name = "per_bl_situacao")
    private Boolean perBlSituacao;
    @Column(name = "per_tx_descricao")
    private String perTxDescricao;
    @Column(name = "per_tx_identificador")
    private String perTxIdentificador;

    @CreationTimestamp
    @Column(name = "per_dt_created_at", nullable = false, updatable = false)
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
}
