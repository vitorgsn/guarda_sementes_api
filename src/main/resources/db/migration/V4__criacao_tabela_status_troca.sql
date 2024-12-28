-- Criar tabela stt_status_troca
CREATE TABLE public.stt_status_troca (
                                         stt_nr_id BIGSERIAL PRIMARY KEY,
                                         stt_tx_status VARCHAR(20) NOT NULL,
                                         tro_nr_id_troca UUID NOT NULL,
                                         stt_dt_status_troca TIMESTAMP NOT NULL,
                                         CONSTRAINT fk_stt_tro FOREIGN KEY (tro_nr_id_troca) REFERENCES public.tro_troca(tro_nr_id)
);

-- Remover campo de status da tabela tro_troca
ALTER TABLE public.tro_troca
DROP COLUMN tro_tx_status_troca;