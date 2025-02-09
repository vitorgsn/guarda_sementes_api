DROP TABLE public.trs_troca_semente;

ALTER TABLE public.tro_troca
ADD COLUMN sem_nr_id_semente_destinatario BIGINT NOT NULL;

ALTER TABLE public.tro_troca
ADD COLUMN tro_nr_quantidade_semente_destinatario FLOAT NOT NULL;

ALTER TABLE public.tro_troca
ADD COLUMN sem_nr_id_semente_remetente BIGINT NOT NULL;

ALTER TABLE public.tro_troca
ADD COLUMN tro_nr_quantidade_semente_remetente FLOAT NOT NULL;

ALTER TABLE public.tro_troca
ADD CONSTRAINT fk_troca_semente_destinatario
FOREIGN KEY (sem_nr_id_semente_destinatario) REFERENCES public.sem_semente(sem_nr_id);

ALTER TABLE public.tro_troca
ADD CONSTRAINT fk_troca_semente_remetente
FOREIGN KEY (sem_nr_id_semente_remetente) REFERENCES public.sem_semente(sem_nr_id);

DROP TABLE public.amu_armazem_usuario;

ALTER TABLE public.arm_armazem
ADD COLUMN usu_nr_id UUID NOT NULL;

ALTER TABLE public.arm_armazem
ADD CONSTRAINT fk_armazem_usuario
FOREIGN KEY (usu_nr_id) REFERENCES public.usu_usuario(usu_nr_id);