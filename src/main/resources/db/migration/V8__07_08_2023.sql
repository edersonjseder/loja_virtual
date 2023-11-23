ALTER TABLE public.produto ADD COLUMN marca VARCHAR(100) NOT NULL;

ALTER TABLE public.produto ADD COLUMN modelo VARCHAR(100) NOT NULL;
ALTER TABLE public.produto ADD CONSTRAINT modelo_unique UNIQUE (modelo);