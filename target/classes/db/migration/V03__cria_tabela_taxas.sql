CREATE TABLE public.taxas
(
    codigo serial NOT NULL,
    data date,
    codigo_socio integer,
    valor float,
    PRIMARY KEY (codigo)
);

ALTER TABLE public.taxas
    ADD FOREIGN KEY (codigo_socio)
    REFERENCES public.socios (codigo)
    NOT VALID;
