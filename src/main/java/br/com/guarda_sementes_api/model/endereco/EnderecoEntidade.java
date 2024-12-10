package br.com.guarda_sementes_api.model.endereco;

import br.com.guarda_sementes_api.model.cidade.CidadeEntidade;
import br.com.guarda_sementes_api.model.usuario.UsuarioEntidade;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    @Column(name = "end_tx_bairro", nullable = false, length = 100)
    private String endTxBairro;

    @Column(name = "end_tx_logradouro", nullable = false, length = 200)
    private String endTxLogradouro;

    @Column(name = "end_tx_numero", nullable = false, length = 100)
    private String endTxNumero;

    @Column(name = "end_tx_referencia", nullable = false, length = 200)
    private String endTxReferencia;

    @CreationTimestamp
    @Column(name = "end_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime endDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "end_dt_updated_at")
    private LocalDateTime endDtUpdateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false, referencedColumnName = "usu_nr_id")
    private UsuarioEntidade usuNrId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid_nr_id", nullable = false, referencedColumnName = "cid_nr_id")
    private CidadeEntidade cidNrId;
}
