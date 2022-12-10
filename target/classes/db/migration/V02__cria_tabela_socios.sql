CREATE TABLE public.socios
(
    codigo bigserial NOT NULL,
    nome text,
    cargo text,
    status text DEFAULT 'ATIVO',
    data_criacao date,
    pendente boolean,
    codigo_empresa bigint,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.socios
    ADD FOREIGN KEY (codigo_empresa)
    REFERENCES public.empresas (codigo)
    NOT VALID;
