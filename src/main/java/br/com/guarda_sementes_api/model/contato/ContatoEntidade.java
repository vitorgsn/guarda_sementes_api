package br.com.guarda_sementes_api.model.contato;

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
@Table(name = "con_contato")
public class ContatoEntidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "con_nr_id")
    private Long conNrId;

    @Column(name = "con_tx_email", nullable = false, length = 100)
    private String conTxEmail;

    @Column(name = "con_tx_numero", nullable = false, length = 20)
    private String conTxNumero;

    @CreationTimestamp
    @Column(name = "con_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime conDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "con_dt_updated_at")
    private LocalDateTime conDtUpdateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usu_nr_id", nullable = false, referencedColumnName = "usu_nr_id")
    private UsuarioEntidade usuNrId;
}
