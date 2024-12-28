-- Criar tabela fts_feira_troca_semente
CREATE TABLE public.fts_feira_troca_semente (
                                                fts_nr_id BIGSERIAL PRIMARY KEY,
                                                fts_bl_disponivel BOOLEAN NOT NULL,
                                                fts_nr_quantidade FLOAT NOT NULL,
                                                sem_nr_id_semente BIGSERIAL NOT NULL,
                                                fts_dt_created_at TIMESTAMP NOT NULL,
                                                fts_dt_updated_at TIMESTAMP NULL,
                                                CONSTRAINT fk_fts_sem FOREIGN KEY (sem_nr_id_semente) REFERENCES public.sem_semente(sem_nr_id)
);