ALTER TABLE public.conta_pagar DROP CONSTRAINT pessoa_fk;
ALTER TABLE public.conta_pagar ADD CONSTRAINT pessoa_fk FOREIGN KEY (pessoa_id) REFERENCES public.pessoa_fisica(id);