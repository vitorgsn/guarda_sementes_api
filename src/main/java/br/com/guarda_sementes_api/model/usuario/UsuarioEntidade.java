package br.com.guarda_sementes_api.model.usuario;

import br.com.guarda_sementes_api.model.armazem.ArmazemEntidade;
import br.com.guarda_sementes_api.model.contato.ContatoEntidade;
import br.com.guarda_sementes_api.model.endereco.EnderecoEntidade;
import br.com.guarda_sementes_api.model.troca.TrocaEntidade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "usu_tx_nome", nullable = false, length = 200)
    private String usuTxNome;

    @Column(name = "usu_tx_login", nullable = false, unique = true, length = 100)
    private String usuTxLogin;

    @Column(name = "usu_tx_senha", nullable = false, length = 256)
    private String usuTxSenha;

    @Column(name = "usu_bl_ativo", nullable = false)
    @Builder.Default
    private boolean usuBlAtivo = false;

    @Column(name = "usu_bl_conta_bloqueada", nullable = false)
    @Builder.Default
    private boolean usuBlContaBloqueada = true;

    @Column(name = "usu_bl_conta_expirada", nullable = false)
    @Builder.Default
    private boolean usuBlContaExpirada = true;

    @Column(name = "usu_bl_credencial_expirada", nullable = false)
    @Builder.Default
    private boolean usuBlCredencialExpirada = true;

    @CreationTimestamp
    @Column(name = "usu_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime usuDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "usu_dt_updated_at")
    private LocalDateTime usuDtUpdateAt;

    @OneToMany(mappedBy = "puObjUsuario", fetch = FetchType.EAGER)
    @Builder.Default
    private List<PerfilUsuarioRelacionamento> usuListPerfis = new ArrayList<>();

    @OneToMany(mappedBy = "usuNrId", fetch = FetchType.LAZY)
    @Builder.Default
    private List<EnderecoEntidade> usuListEndereco = new ArrayList<>();

    @OneToMany(mappedBy = "usuNrId", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ContatoEntidade> usuListContato = new ArrayList<>();

    @OneToMany(mappedBy = "usuNrId", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ArmazemEntidade> usuListArmazem = new ArrayList<>();

    @OneToMany(mappedBy = "usuNrIdRemetente", fetch = FetchType.LAZY)
    @Builder.Default
    private List<TrocaEntidade> usuListTrocaRemetente = new ArrayList<>();

    @OneToMany(mappedBy = "usuNrIdDestinatario", fetch = FetchType.LAZY)
    @Builder.Default
    private List<TrocaEntidade> usuListTrocaDestinatario = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return usuListPerfis.stream()
                .map(PerfilUsuarioRelacionamento::getPuObjPerfil)
                .toList();
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
        return usuBlContaExpirada;
    }
}
