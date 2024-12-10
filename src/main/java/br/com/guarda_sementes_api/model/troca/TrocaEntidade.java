package br.com.guarda_sementes_api.model.troca;

import br.com.guarda_sementes_api.model.semente.SementeEntidade;
import br.com.guarda_sementes_api.model.troca.enuns.StatusTroca;
import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tro_troca")
public class TrocaEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tro_nr_id")
    private UUID troNrId;

    @Column(name = "tro_tx_instruncoes", nullable = false, length = 256)
    private String troTxInstruncoes;

    @Column(name = "tro_tx_status_troca", nullable = false)
    @Builder.Default
    private StatusTroca troTxStatusTroca = StatusTroca.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id_remetente", nullable = false, referencedColumnName = "usu_nr_id")
    private UsuarioEntidade usuNrIdRemetente;

    @OneToMany(mappedBy = "troNrId", fetch = FetchType.LAZY)
    @Builder.Default
    private List<SementeEntidade> troListSemente = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id_destinatario", nullable = false, referencedColumnName = "usu_nr_id")
    private UsuarioEntidade usuNrIdDestinatario;

    @CreationTimestamp
    @Column(name = "tro_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime troDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "tro_dt_updated_at")
    private LocalDateTime troDtUpdateAt;

}
