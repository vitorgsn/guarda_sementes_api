package br.com.guarda_sementes_api.model.instruncao;

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
@Table(name = "ins_instruncao")
public class InstruncaoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ins_nr_id")
    private Long insNrId;

    @Column(name = "ins_tx_titulo", nullable = false, length = 200)
    private String insTxTitulo;

    @Column(name = "ins_tx_instruncao", nullable = false, length = 256)
    private String insTxInstruncao;

    @CreationTimestamp
    @Column(name = "ins_dt_created_at", nullable = false, updatable = false)
    private LocalDateTime insDtCreatedAt;

    @UpdateTimestamp
    @Column(name = "ins_dt_updated_at")
    private LocalDateTime insDtUpdateAt;
}
