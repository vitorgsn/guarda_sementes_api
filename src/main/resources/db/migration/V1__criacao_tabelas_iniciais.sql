CREATE TABLE public.cta_categoria_armazem (
                                              cta_nr_id BIGSERIAL PRIMARY KEY,
                                              cta_dt_created_at TIMESTAMP NOT NULL,
                                              cta_dt_updated_at TIMESTAMP NULL,
                                              cta_tx_descricao VARCHAR(256) NULL,
                                              cta_tx_nome VARCHAR(100) NOT NULL,
                                              cta_bl_ativo BOOLEAN NOT NULL
);

CREATE TABLE public.est_estado (
                                   est_nr_id BIGSERIAL PRIMARY KEY,
                                   est_dt_created_at TIMESTAMP NOT NULL,
                                   est_dt_updated_at TIMESTAMP NULL,
                                   est_tx_nome VARCHAR(100) NOT NULL,
                                   est_tx_sigla VARCHAR(2) NOT NULL,
                                   est_bl_ativo BOOLEAN NOT NULL
);

CREATE TABLE public.ins_instruncao (
                                       ins_nr_id BIGSERIAL PRIMARY KEY,
                                       ins_dt_created_at TIMESTAMP NOT NULL,
                                       ins_dt_updated_at TIMESTAMP NULL,
                                       ins_tx_instruncao VARCHAR(256) NOT NULL,
                                       ins_tx_titulo VARCHAR(200) NOT NULL,
                                       ins_bl_ativo BOOLEAN NOT NULL
);

CREATE TABLE public.per_perfil (
                                   per_nr_id BIGSERIAL PRIMARY KEY,
                                   per_bl_ativo BOOLEAN NOT NULL,
                                   per_dt_created_at TIMESTAMP NOT NULL,
                                   per_dt_updated_at TIMESTAMP NULL,
                                   per_tx_descricao VARCHAR(255) NULL,
                                   per_tx_identificador VARCHAR(255) NULL,
                                   per_tx_nome VARCHAR(255) NOT NULL,
                                   CONSTRAINT uk_per_tx_nome UNIQUE (per_tx_nome)
);

CREATE TABLE public.usu_usuario (
                                    usu_nr_id UUID PRIMARY KEY,
                                    usu_bl_ativo BOOLEAN NOT NULL,
                                    usu_bl_conta_bloqueada BOOLEAN NOT NULL,
                                    usu_bl_conta_expirada BOOLEAN NOT NULL,
                                    usu_bl_credencial_expirada BOOLEAN NOT NULL,
                                    usu_dt_created_at TIMESTAMP NOT NULL,
                                    usu_dt_updated_at TIMESTAMP NULL,
                                    usu_tx_login VARCHAR(100) NOT NULL,
                                    usu_tx_nome VARCHAR(200) NOT NULL,
                                    usu_tx_senha VARCHAR(256) NOT NULL,
                                    CONSTRAINT uk_usu_tx_login UNIQUE (usu_tx_login)
);

CREATE TABLE public.arm_armazem (
                                    arm_nr_id BIGSERIAL PRIMARY KEY,
                                    arm_dt_created_at TIMESTAMP NOT NULL,
                                    arm_dt_updated_at TIMESTAMP NULL,
                                    arm_tx_descricao VARCHAR(256) NULL,
                                    arm_bl_ativo BOOLEAN NOT NULL,
                                    cta_nr_id BIGSERIAL NOT NULL,
                                    CONSTRAINT fk_arm_cta FOREIGN KEY (cta_nr_id) REFERENCES public.cta_categoria_armazem(cta_nr_id)
);

CREATE TABLE public.cid_cidade (
                                   cid_nr_id BIGSERIAL PRIMARY KEY,
                                   cid_dt_created_at TIMESTAMP NOT NULL,
                                   cid_dt_updated_at TIMESTAMP NULL,
                                   cid_tx_nome VARCHAR(100) NOT NULL,
                                   cid_bl_ativo BOOLEAN NOT NULL,
                                   est_nr_id BIGSERIAL NOT NULL,
                                   CONSTRAINT fk_cid_est FOREIGN KEY (est_nr_id) REFERENCES public.est_estado(est_nr_id)
);

CREATE TABLE public.con_contato (
                                    con_nr_id BIGSERIAL PRIMARY KEY,
                                    con_dt_created_at TIMESTAMP NOT NULL,
                                    con_dt_updated_at TIMESTAMP NULL,
                                    con_tx_email VARCHAR(100) NOT NULL,
                                    con_tx_numero VARCHAR(20) NOT NULL,
                                    con_bl_ativo BOOLEAN NOT NULL
);

CREATE TABLE public.cou_contato_usuario (
                                            cou_nr_id BIGSERIAL PRIMARY KEY,
                                            usu_nr_id UUID NOT NULL,
                                            con_nr_id BIGSERIAL NOT NULL,
                                            CONSTRAINT fk_cou_usu FOREIGN KEY (usu_nr_id) REFERENCES public.usu_usuario(usu_nr_id),
                                            CONSTRAINT fk_cou_con FOREIGN KEY (con_nr_id) REFERENCES public.con_contato(con_nr_id)
);

CREATE TABLE public.end_endereco (
                                     end_nr_id BIGSERIAL PRIMARY KEY,
                                     end_dt_created_at TIMESTAMP NOT NULL,
                                     end_dt_updated_at TIMESTAMP NULL,
                                     end_tx_bairro VARCHAR(100) NOT NULL,
                                     end_tx_logradouro VARCHAR(200) NOT NULL,
                                     end_tx_numero VARCHAR(100) NOT NULL,
                                     end_tx_referencia VARCHAR(200) NOT NULL,
                                     end_bl_ativo BOOLEAN NOT NULL,
                                     cid_nr_id BIGSERIAL NOT NULL,
                                     CONSTRAINT fk_end_cid FOREIGN KEY (cid_nr_id) REFERENCES public.cid_cidade(cid_nr_id)
);

CREATE TABLE public.enu_endereco_usuario (
                                             enu_nr_id BIGSERIAL PRIMARY KEY,
                                             usu_nr_id UUID NOT NULL,
                                             end_nr_id BIGSERIAL NOT NULL,
                                             CONSTRAINT fk_enu_usu FOREIGN KEY (usu_nr_id) REFERENCES public.usu_usuario(usu_nr_id),
                                             CONSTRAINT fk_enu_end FOREIGN KEY (end_nr_id) REFERENCES public.end_endereco(end_nr_id)
);

CREATE TABLE public.pu_perfil_usuario (
                                          pu_nr_id BIGSERIAL PRIMARY KEY,
                                          per_nr_id BIGSERIAL NOT NULL,
                                          usu_nr_id UUID NOT NULL,
                                          CONSTRAINT fk_pu_per FOREIGN KEY (per_nr_id) REFERENCES public.per_perfil(per_nr_id),
                                          CONSTRAINT fk_pu_usu FOREIGN KEY (usu_nr_id) REFERENCES public.usu_usuario(usu_nr_id)
);

CREATE TABLE public.tro_troca (
                                  tro_nr_id UUID PRIMARY KEY,
                                  tro_dt_created_at TIMESTAMP NOT NULL,
                                  tro_dt_updated_at TIMESTAMP NULL,
                                  tro_tx_instruncoes VARCHAR(256) NOT NULL,
                                  tro_tx_status_troca VARCHAR(30) NOT NULL,
                                  usu_nr_id_destinatario UUID NOT NULL,
                                  usu_nr_id_remetente UUID NOT NULL,
                                  CONSTRAINT fk_tro_usu_dest FOREIGN KEY (usu_nr_id_destinatario) REFERENCES public.usu_usuario(usu_nr_id),
                                  CONSTRAINT fk_tro_usu_remet FOREIGN KEY (usu_nr_id_remetente) REFERENCES public.usu_usuario(usu_nr_id)
);

CREATE TABLE public.sem_semente (
                                    sem_nr_id BIGSERIAL PRIMARY KEY,
                                    sem_dt_created_at TIMESTAMP NOT NULL,
                                    sem_dt_updated_at TIMESTAMP NULL,
                                    sem_nr_quantidade FLOAT NOT NULL,
                                    sem_tx_descricao VARCHAR(256) NULL,
                                    sem_tx_nome VARCHAR(200) NOT NULL,
                                    sem_bl_ativo BOOLEAN NOT NULL,
                                    arm_nr_id BIGSERIAL NOT NULL,
                                    CONSTRAINT fk_sem_arm FOREIGN KEY (arm_nr_id) REFERENCES public.arm_armazem(arm_nr_id)
);

CREATE TABLE public.amu_armazem_usuario (
                                            amu_nr_id BIGSERIAL PRIMARY KEY,
                                            usu_nr_id UUID NOT NULL,
                                            arm_nr_id BIGSERIAL NOT NULL,
                                            CONSTRAINT fk_amu_usu FOREIGN KEY (usu_nr_id) REFERENCES public.usu_usuario(usu_nr_id),
                                            CONSTRAINT fk_amu_arm FOREIGN KEY (arm_nr_id) REFERENCES public.arm_armazem(arm_nr_id)
);

CREATE TABLE public.trs_troca_semente (
                                          trs_nr_id BIGSERIAL PRIMARY KEY,
                                          tro_nr_id UUID NOT NULL,
                                          sem_nr_id BIGSERIAL NOT NULL,
                                          CONSTRAINT fk_trs_tro FOREIGN KEY (tro_nr_id) REFERENCES public.tro_troca(tro_nr_id),
                                          CONSTRAINT fk_trs_sem FOREIGN KEY (sem_nr_id) REFERENCES public.sem_semente(sem_nr_id)
);