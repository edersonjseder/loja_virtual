ALTER TABLE public.produto DROP CONSTRAINT uk_p18v18qgvx08gox1n8cerjwil;
ALTER TABLE public.produto ADD CONSTRAINT modelo_unique UNIQUE (modelo);