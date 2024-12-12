package br.com.guarda_sementes_api.model.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "usu_usuario")
public class UsuarioEntidade implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usu_nr_id")
    private UUID usuNrId;

    @Column(name = "usu_tx_nome")
    private String usuTxNome;

    @Column(name = "usu_tx_login")
    private String usuTxLogin;

    @Column(name = "usu_tx_senha")
    private String usuTxSenha;

    @Column(name = "usu_bl_ativo")
    @Builder.Default
    private boolean usuBlAtivo = Boolean.TRUE;

    @Column(name = "usu_bl_conta_bloqueada")
    @Builder.Default
    private boolean usuBlContaBloqueada = Boolean.FALSE;

    @Column(name = "usu_bl_conta_expirada")
    @Builder.Default
    private boolean usuBlContaExpirada = Boolean.FALSE;

    @Column(name = "usu_bl_credencial_expirada")
    @Builder.Default
    private boolean usuBlCredencialExpirada = Boolean.FALSE;

    @CreationTimestamp
    @Column(name = "usu_dt_created_at")
    private LocalDateTime usuDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "usu_dt_updated_at")
    private LocalDateTime usuDtUpdateAt;

    @OneToMany(mappedBy = "puObjUsuario", fetch = FetchType.EAGER)
    @Builder.Default
    private List<PerfilUsuarioRelacionamento> usuListPerfis = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuListPerfis.stream()
                .map(PerfilUsuarioRelacionamento::getPuObjPerfil)  // Obtém o perfil
                .map(PerfilEntidade::getAuthority)  // Obtém o identificador do perfil
                .map(SimpleGrantedAuthority::new)  // Cria uma autoridade a partir do identificador
                .collect(Collectors.toList());  // Coleta em uma lista
    }

    @Override
    public String getPassword() {
        return usuTxSenha;
    }

    @Override
    public String getUsername() {
        return usuTxLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !usuBlContaExpirada;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !usuBlContaBloqueada;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !usuBlCredencialExpirada;
    }

    @Override
    public boolean isEnabled() {
        return usuBlAtivo;
    }
}
