ALTER TABLE public.categoria_produto DROP CONSTRAINT uk_1y8nlugc4nwmnscgago4u5qu9;
ALTER TABLE public.categoria_produto ADD CONSTRAINT nome_unique UNIQUE (nome);