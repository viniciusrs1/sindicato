ALTER TABLE public.empresas DISABLE TRIGGER ALL;
ALTER TABLE public.socios DISABLE TRIGGER ALL;
ALTER TABLE public.taxas DISABLE TRIGGER ALL;

SET session_replication_role TO replica;

delete from public.empresas;
delete from public.socios;
delete from public.taxas;

ALTER SEQUENCE public.empresas_codigo_seq RESTART WITH 1;
UPDATE public.empresas SET codigo=nextval('public.empresas_codigo_seq');

ALTER SEQUENCE public.socios_codigo_seq RESTART WITH 1;
UPDATE public.socios SET codigo=nextval('public.socios_codigo_seq');

ALTER SEQUENCE public.taxas_codigo_seq RESTART WITH 1;
UPDATE public.taxas SET codigo=nextval('public.taxas_codigo_seq');

SET session_replication_role TO default;

ALTER TABLE public.empresas ENABLE TRIGGER ALL;
ALTER TABLE public.socios ENABLE TRIGGER ALL;
ALTER TABLE public.taxas ENABLE TRIGGER ALL;

INSERT INTO public.empresas(nome, taxa, status, data_criacao, taxas_atualizadas) VALUES
('Cemig', 300, 'ATIVO', '09/12/2022', true),
('Codau', 400, 'ATIVO', '31/10/2022', true),
('Codiub Solucao Digital', 550, 'ATIVO', '01/10/2021', true),
('Globo Comunicacoes', 500, 'ATIVO', '25/04/2019', false),
('Magalu', 900, 'ATIVO', '31/10/2022', true),
('Algar Telecom', 400, 'ATIVO', '31/10/2022', false);

INSERT INTO public.socios(nome, cargo, status, data_criacao, pendente, codigo_empresa) VALUES 
('Vinicius Rodrigues de Sousa', 'Desenvolvedor de Software', 'ATIVO', '09/12/2022', true, 4),
('Lindovaldo Costa Leao', 'Desenvolvedor de Software', 'ATIVO', '09/12/2022', true, 3),
('Caio Freitas Lima', 'UX Designer', 'ATIVO', '09/12/2022', true, 3),
('Ronilçdo Cesar de Moura', 'Professor', 'ATIVO', '09/12/2022', true, 1),
('Breno Henrique de Sousa', 'Desenvolvedor de Software', 'ATIVO', '09/12/2022', true, 2),
('Guilherme Mutao Jose', 'Desenvolvedor de Software', 'ATIVO', '09/12/2022', true, 5),
('Guilherme Barbosa Broxa', 'Desenvolvedor de Software', 'ATIVO', '09/12/2022', true, 3),
('Joao Floriano', 'Coordenador de Desenvolvimento', 'ATIVO', '09/12/2022', true, 6),
('Rafael Orbolato', 'Professor', 'ATIVO', '09/12/2022', true, 6);

INSERT INTO public.taxas(data, codigo_socio, valor) VALUES
('09/12/2022', 1, 300),
('09/11/2022', 1, 300),
('09/10/2022', 1, 300),
('09/09/2022', 1, 300),
('09/12/2022', 2, 400),
('09/11/2022', 2, 400),
('09/10/2022', 2, 400),
('09/12/2022', 5, 350),
('09/11/2022', 5, 350),
('09/10/2022', 5, 350),
('09/10/2022', 3, 300),
('09/11/2022', 3, 300),
('09/10/2022', 4, 300),
('09/11/2022', 4, 300),
('09/12/2022', 6, 300),
('09/12/2022', 7, 300),
('09/12/2022', 8, 300),
('09/12/2022', 9, 300);


