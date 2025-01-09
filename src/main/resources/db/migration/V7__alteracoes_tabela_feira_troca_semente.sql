-- Renomear tabela e campos, sementes disponíveis para troca
ALTER TABLE public.fts_feira_troca_semente RENAME COLUMN fts_nr_id TO sdt_nr_id;
ALTER TABLE public.fts_feira_troca_semente RENAME COLUMN fts_bl_disponivel TO sdt_bl_disponivel;
ALTER TABLE public.fts_feira_troca_semente RENAME COLUMN fts_nr_quantidade TO sdt_nr_quantidade;
ALTER TABLE public.fts_feira_troca_semente RENAME COLUMN fts_dt_created_at TO sdt_dt_created_at;
ALTER TABLE public.fts_feira_troca_semente RENAME COLUMN fts_dt_updated_at TO sdt_dt_updated_at;
ALTER TABLE public.fts_feira_troca_semente ADD COLUMN sdt_tx_observacoes VARCHAR (256) NULL;
ALTER TABLE public.fts_feira_troca_semente RENAME TO sdt_semente_disponivel_troca;
