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

CREATE TABLE public.taxas
(
    codigo serial NOT NULL,
    data date,
    codigo_socio integer,
    valor float,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.socios
    ADD FOREIGN KEY (codigo_empresa)
    REFERENCES public.empresas (codigo)
    NOT VALID;
    
ALTER TABLE public.taxas
    ADD FOREIGN KEY (codigo_socio)
    REFERENCES public.socios (codigo)
    NOT VALID;