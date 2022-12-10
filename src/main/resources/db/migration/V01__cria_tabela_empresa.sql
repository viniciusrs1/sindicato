CREATE TABLE public.empresas
(
    codigo bigserial NOT NULL,
    nome text,
    taxa float,
    status text DEFAULT 'ATIVO',
    data_criacao date,
    taxas_atualizadas boolean,
    PRIMARY KEY (codigo)
);
