CREATE TABLE IF NOT EXISTS public.acesso_end_point
(
    nome_end_point character varying COLLATE pg_catalog."default",
    qtd_acesso_end_point integer
);

insert into public.acesso_end_point(nome_end_point, qtd_acesso_end_point) VALUES ('END-POINT-NOME-PESSOA-FISICA', 0);

alter table public.acesso_end_point add constraint nome_end_point_unique unique (nome_end_point);