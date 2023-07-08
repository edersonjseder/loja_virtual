--
-- PostgreSQL database dump
--

-- Dumped from database version 14.8 (Ubuntu 14.8-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.8 (Ubuntu 14.8-0ubuntu0.22.04.1)

-- Started on 2023-07-02 10:55:20 EDT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3567 (class 1262 OID 16385)
-- Name: lojavirtual; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE lojavirtual WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.UTF-8';


ALTER DATABASE lojavirtual OWNER TO postgres;

\connect lojavirtual

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 249 (class 1255 OID 16671)
-- Name: validachavepessoa(); Type: FUNCTION; Schema: public; Owner: edersonjseder
--

CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

declare existe integer;

begin
	existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_id);
    if (existe <= 0) then
	existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_id);
    if (existe <= 0) then
    	RAISE EXCEPTION 'Nao foi encontrado o ID e PK da pessoa para realizar a associacao do cadastro';
     end if;
    end if;
	return new;
end;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO edersonjseder;

--
-- TOC entry 250 (class 1255 OID 16674)
-- Name: validachavepessoafunc(); Type: FUNCTION; Schema: public; Owner: edersonjseder
--

CREATE FUNCTION public.validachavepessoafunc() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

declare existe integer;

begin
	existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_fornecedor_id);
    if (existe <= 0) then
	existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_fornecedor_id);
    if (existe <= 0) then
    	RAISE EXCEPTION 'Nao foi encontrado o ID e PK da pessoa para realizar a associacao do cadastro';
     end if;
    end if;
	return new;
end;
$$;


ALTER FUNCTION public.validachavepessoafunc() OWNER TO edersonjseder;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 213 (class 1259 OID 16402)
-- Name: acesso; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.acesso (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.acesso OWNER TO edersonjseder;

--
-- TOC entry 246 (class 1259 OID 16615)
-- Name: avaliacao_produto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.avaliacao_produto (
    id bigint NOT NULL,
    pessoa_id bigint NOT NULL,
    produto_id bigint NOT NULL,
    nota integer NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.avaliacao_produto OWNER TO edersonjseder;

--
-- TOC entry 211 (class 1259 OID 16394)
-- Name: categoria_produto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.categoria_produto (
    id bigint NOT NULL,
    descricao character varying(255),
    nome character varying(255)
);


ALTER TABLE public.categoria_produto OWNER TO edersonjseder;

--
-- TOC entry 227 (class 1259 OID 16472)
-- Name: conta_pagar; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.conta_pagar (
    id bigint NOT NULL,
    data_pagamento date,
    pessoa_id bigint NOT NULL,
    pessoa_fornecedor_id bigint NOT NULL,
    data_vencimento date NOT NULL,
    descricao character varying(255) NOT NULL,
    status_conta_pagar character varying(255) NOT NULL,
    valor_total numeric(38,2) NOT NULL
);


ALTER TABLE public.conta_pagar OWNER TO edersonjseder;

--
-- TOC entry 223 (class 1259 OID 16456)
-- Name: conta_receber; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.conta_receber (
    id bigint NOT NULL,
    data_pagamento date,
    valor_desconto numeric(38,2),
    pessoa_id bigint NOT NULL,
    data_vencimento date NOT NULL,
    descricao character varying(255) NOT NULL,
    status_conta_receber character varying(255) NOT NULL,
    valor_total numeric(38,2) NOT NULL
);


ALTER TABLE public.conta_receber OWNER TO edersonjseder;

--
-- TOC entry 229 (class 1259 OID 16480)
-- Name: cupom_desconto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.cupom_desconto (
    id bigint NOT NULL,
    valor_porcentagem_desconto numeric(38,2),
    valor_real_desconto numeric(38,2),
    codigo_desconto character varying(255) NOT NULL,
    data_validade_cupom date NOT NULL
);


ALTER TABLE public.cupom_desconto OWNER TO edersonjseder;

--
-- TOC entry 218 (class 1259 OID 16423)
-- Name: endereco; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.endereco (
    id bigint NOT NULL,
    complemento character varying(255),
    pessoa_id bigint NOT NULL,
    bairro character varying(255) NOT NULL,
    cep character varying(255) NOT NULL,
    cidade character varying(255) NOT NULL,
    estado character varying(255) NOT NULL,
    numero character varying(255) NOT NULL,
    rua_logradouro character varying(255) NOT NULL,
    tipo_endereco character varying(255) NOT NULL,
    uf character varying(255) NOT NULL
);


ALTER TABLE public.endereco OWNER TO edersonjseder;

--
-- TOC entry 225 (class 1259 OID 16466)
-- Name: forma_pagamento; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.forma_pagamento (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.forma_pagamento OWNER TO edersonjseder;

--
-- TOC entry 235 (class 1259 OID 16503)
-- Name: imagem_produto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.imagem_produto (
    id bigint NOT NULL,
    produto_id bigint NOT NULL,
    imagem_miniatura text NOT NULL,
    imagem_original text NOT NULL
);


ALTER TABLE public.imagem_produto OWNER TO edersonjseder;

--
-- TOC entry 244 (class 1259 OID 16599)
-- Name: item_venda_loja; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.item_venda_loja (
    id bigint NOT NULL,
    produto_id bigint NOT NULL,
    venda_compra_loja_id bigint NOT NULL,
    quantidade double precision NOT NULL
);


ALTER TABLE public.item_venda_loja OWNER TO edersonjseder;

--
-- TOC entry 209 (class 1259 OID 16386)
-- Name: marca_produto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.marca_produto (
    id bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.marca_produto OWNER TO edersonjseder;

--
-- TOC entry 237 (class 1259 OID 16516)
-- Name: nota_fiscal_compra; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.nota_fiscal_compra (
    id bigint NOT NULL,
    descricao_obs character varying(255),
    valor_desconto numeric(38,2),
    conta_pagar_id bigint,
    pessoa_id bigint NOT NULL,
    data_compra date NOT NULL,
    numero_nota character varying(255) NOT NULL,
    serie_nota character varying(255) NOT NULL,
    valor_icms numeric(38,2) NOT NULL,
    valor_total numeric(38,2) NOT NULL
);


ALTER TABLE public.nota_fiscal_compra OWNER TO edersonjseder;

--
-- TOC entry 241 (class 1259 OID 16550)
-- Name: nota_fiscal_venda; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.nota_fiscal_venda (
    id bigint NOT NULL,
    venda_compra_loja_id bigint NOT NULL,
    numero character varying(255) NOT NULL,
    pdf text NOT NULL,
    serie character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    xml text NOT NULL
);


ALTER TABLE public.nota_fiscal_venda OWNER TO edersonjseder;

--
-- TOC entry 238 (class 1259 OID 16523)
-- Name: nota_item_produto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.nota_item_produto (
    id bigint NOT NULL,
    nota_fiscal_compra_id bigint NOT NULL,
    produto_id bigint NOT NULL,
    quantidade double precision NOT NULL
);


ALTER TABLE public.nota_item_produto OWNER TO edersonjseder;

--
-- TOC entry 214 (class 1259 OID 16407)
-- Name: pessoa_fisica; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.pessoa_fisica (
    id bigint NOT NULL,
    cpf character varying(255) NOT NULL,
    data_nascimento date,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    telefone character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_fisica OWNER TO edersonjseder;

--
-- TOC entry 215 (class 1259 OID 16414)
-- Name: pessoa_juridica; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.pessoa_juridica (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    nome character varying(255) NOT NULL,
    telefone character varying(255) NOT NULL,
    categoria character varying(255),
    cnpj character varying(255) NOT NULL,
    inscricao_estadual character varying(255) NOT NULL,
    inscricao_municipal character varying(255),
    nome_fantasia character varying(255) NOT NULL,
    razao_social character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_juridica OWNER TO edersonjseder;

--
-- TOC entry 231 (class 1259 OID 16486)
-- Name: produto; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.produto (
    id bigint NOT NULL,
    alerta_estoque boolean,
    link_youtube character varying(255),
    qtde_alerta bigint,
    qtde_clique integer,
    alerta_qtde_estoque boolean,
    altura double precision NOT NULL,
    ativo boolean NOT NULL,
    descricao text NOT NULL,
    largura double precision NOT NULL,
    nome character varying(255) NOT NULL,
    peso double precision NOT NULL,
    profundidade double precision NOT NULL,
    qtde_estoque integer NOT NULL,
    tipo_unidade character varying(255) NOT NULL,
    valor_venda numeric(38,2) NOT NULL
);


ALTER TABLE public.produto OWNER TO edersonjseder;

--
-- TOC entry 216 (class 1259 OID 16421)
-- Name: seq_acesso; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_acesso OWNER TO edersonjseder;

--
-- TOC entry 247 (class 1259 OID 16620)
-- Name: seq_avaliacao_produto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_avaliacao_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_avaliacao_produto OWNER TO edersonjseder;

--
-- TOC entry 212 (class 1259 OID 16401)
-- Name: seq_categoria_produto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_categoria_produto OWNER TO edersonjseder;

--
-- TOC entry 228 (class 1259 OID 16479)
-- Name: seq_conta_pagar; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_conta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_pagar OWNER TO edersonjseder;

--
-- TOC entry 224 (class 1259 OID 16465)
-- Name: seq_conta_receber; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_conta_receber OWNER TO edersonjseder;

--
-- TOC entry 230 (class 1259 OID 16485)
-- Name: seq_cupom_desconto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_cupom_desconto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_cupom_desconto OWNER TO edersonjseder;

--
-- TOC entry 219 (class 1259 OID 16430)
-- Name: seq_endereco; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_endereco OWNER TO edersonjseder;

--
-- TOC entry 226 (class 1259 OID 16471)
-- Name: seq_forma_pagamento; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_forma_pagamento OWNER TO edersonjseder;

--
-- TOC entry 236 (class 1259 OID 16510)
-- Name: seq_imagem_produto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_imagem_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_imagem_produto OWNER TO edersonjseder;

--
-- TOC entry 245 (class 1259 OID 16604)
-- Name: seq_item_venda_loja; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_item_venda_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_item_venda_loja OWNER TO edersonjseder;

--
-- TOC entry 210 (class 1259 OID 16393)
-- Name: seq_marca_produto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_marca_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_marca_produto OWNER TO edersonjseder;

--
-- TOC entry 239 (class 1259 OID 16528)
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_nota_fiscal_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_compra OWNER TO edersonjseder;

--
-- TOC entry 242 (class 1259 OID 16557)
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_fiscal_venda OWNER TO edersonjseder;

--
-- TOC entry 240 (class 1259 OID 16529)
-- Name: seq_nota_item_produto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_nota_item_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_nota_item_produto OWNER TO edersonjseder;

--
-- TOC entry 217 (class 1259 OID 16422)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_pessoa OWNER TO edersonjseder;

--
-- TOC entry 233 (class 1259 OID 16500)
-- Name: seq_produto; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_produto OWNER TO edersonjseder;

--
-- TOC entry 234 (class 1259 OID 16501)
-- Name: seq_status_rastreio; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_status_rastreio OWNER TO edersonjseder;

--
-- TOC entry 222 (class 1259 OID 16445)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_usuario OWNER TO edersonjseder;

--
-- TOC entry 243 (class 1259 OID 16563)
-- Name: seq_vd_compra_loja; Type: SEQUENCE; Schema: public; Owner: edersonjseder
--

CREATE SEQUENCE public.seq_vd_compra_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_vd_compra_loja OWNER TO edersonjseder;

--
-- TOC entry 232 (class 1259 OID 16493)
-- Name: status_rastreio; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.status_rastreio (
    id bigint NOT NULL,
    centro_distribuicao character varying(255),
    cidade character varying(255),
    estado character varying(255),
    status character varying(255),
    venda_compra_loja_id bigint NOT NULL
);


ALTER TABLE public.status_rastreio OWNER TO edersonjseder;

--
-- TOC entry 220 (class 1259 OID 16431)
-- Name: usuario; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.usuario (
    id bigint NOT NULL,
    pessoa_id bigint NOT NULL,
    data_atual_senha date NOT NULL,
    login character varying(255) NOT NULL,
    senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO edersonjseder;

--
-- TOC entry 221 (class 1259 OID 16438)
-- Name: usuarios_acesso; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.usuarios_acesso (
    usuario_id bigint NOT NULL,
    acesso_id bigint NOT NULL
);


ALTER TABLE public.usuarios_acesso OWNER TO edersonjseder;

--
-- TOC entry 248 (class 1259 OID 16626)
-- Name: vd_compra_loja; Type: TABLE; Schema: public; Owner: edersonjseder
--

CREATE TABLE public.vd_compra_loja (
    id bigint NOT NULL,
    data_entrega date NOT NULL,
    data_venda date NOT NULL,
    dias_entrega integer NOT NULL,
    valor_desconto numeric(38,2),
    valor_frete numeric(38,2) NOT NULL,
    valor_total numeric(38,2) NOT NULL,
    cupom_desconto_id bigint,
    endereco_cobranca_id bigint NOT NULL,
    endereco_entrega_id bigint NOT NULL,
    forma_pagamento_id bigint NOT NULL,
    nota_fiscal_venda_id bigint NOT NULL,
    pessoa_id bigint NOT NULL
);


ALTER TABLE public.vd_compra_loja OWNER TO edersonjseder;

--
-- TOC entry 3526 (class 0 OID 16402)
-- Dependencies: 213
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3559 (class 0 OID 16615)
-- Dependencies: 246
-- Data for Name: avaliacao_produto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--

INSERT INTO public.avaliacao_produto (id, pessoa_id, produto_id, nota, descricao) VALUES (1, 1, 1, 10, 'Teste avaliacao produto trigger');


--
-- TOC entry 3524 (class 0 OID 16394)
-- Dependencies: 211
-- Data for Name: categoria_produto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3540 (class 0 OID 16472)
-- Dependencies: 227
-- Data for Name: conta_pagar; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3536 (class 0 OID 16456)
-- Dependencies: 223
-- Data for Name: conta_receber; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3542 (class 0 OID 16480)
-- Dependencies: 229
-- Data for Name: cupom_desconto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3531 (class 0 OID 16423)
-- Dependencies: 218
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3538 (class 0 OID 16466)
-- Dependencies: 225
-- Data for Name: forma_pagamento; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3548 (class 0 OID 16503)
-- Dependencies: 235
-- Data for Name: imagem_produto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3557 (class 0 OID 16599)
-- Dependencies: 244
-- Data for Name: item_venda_loja; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3522 (class 0 OID 16386)
-- Dependencies: 209
-- Data for Name: marca_produto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3550 (class 0 OID 16516)
-- Dependencies: 237
-- Data for Name: nota_fiscal_compra; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3554 (class 0 OID 16550)
-- Dependencies: 241
-- Data for Name: nota_fiscal_venda; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3551 (class 0 OID 16523)
-- Dependencies: 238
-- Data for Name: nota_item_produto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3527 (class 0 OID 16407)
-- Dependencies: 214
-- Data for Name: pessoa_fisica; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--

INSERT INTO public.pessoa_fisica (id, cpf, data_nascimento, email, nome, telefone) VALUES (1, '35012365874', '1981-01-12', 'ederson@gmail.com', 'Eder Santos', '98541-9654');


--
-- TOC entry 3528 (class 0 OID 16414)
-- Dependencies: 215
-- Data for Name: pessoa_juridica; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3544 (class 0 OID 16486)
-- Dependencies: 231
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--

INSERT INTO public.produto (id, alerta_estoque, link_youtube, qtde_alerta, qtde_clique, alerta_qtde_estoque, altura, ativo, descricao, largura, nome, peso, profundidade, qtde_estoque, tipo_unidade, valor_venda) VALUES (1, true, 'www.youtube.com', 1, 10, true, 10.5, true, 'produto de teste', 10.2, 'Nome produto de teste', 10.5, 5.4, 3, 'UN', 60.00);


--
-- TOC entry 3545 (class 0 OID 16493)
-- Dependencies: 232
-- Data for Name: status_rastreio; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3533 (class 0 OID 16431)
-- Dependencies: 220
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3534 (class 0 OID 16438)
-- Dependencies: 221
-- Data for Name: usuarios_acesso; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3561 (class 0 OID 16626)
-- Dependencies: 248
-- Data for Name: vd_compra_loja; Type: TABLE DATA; Schema: public; Owner: edersonjseder
--



--
-- TOC entry 3569 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_acesso; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_acesso', 1, false);


--
-- TOC entry 3570 (class 0 OID 0)
-- Dependencies: 247
-- Name: seq_avaliacao_produto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_avaliacao_produto', 1, false);


--
-- TOC entry 3571 (class 0 OID 0)
-- Dependencies: 212
-- Name: seq_categoria_produto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_categoria_produto', 1, false);


--
-- TOC entry 3572 (class 0 OID 0)
-- Dependencies: 228
-- Name: seq_conta_pagar; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_conta_pagar', 1, false);


--
-- TOC entry 3573 (class 0 OID 0)
-- Dependencies: 224
-- Name: seq_conta_receber; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);


--
-- TOC entry 3574 (class 0 OID 0)
-- Dependencies: 230
-- Name: seq_cupom_desconto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_cupom_desconto', 1, false);


--
-- TOC entry 3575 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_endereco; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_endereco', 1, false);


--
-- TOC entry 3576 (class 0 OID 0)
-- Dependencies: 226
-- Name: seq_forma_pagamento; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_forma_pagamento', 1, false);


--
-- TOC entry 3577 (class 0 OID 0)
-- Dependencies: 236
-- Name: seq_imagem_produto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_imagem_produto', 1, false);


--
-- TOC entry 3578 (class 0 OID 0)
-- Dependencies: 245
-- Name: seq_item_venda_loja; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_item_venda_loja', 1, false);


--
-- TOC entry 3579 (class 0 OID 0)
-- Dependencies: 210
-- Name: seq_marca_produto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_marca_produto', 1, false);


--
-- TOC entry 3580 (class 0 OID 0)
-- Dependencies: 239
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_compra', 1, false);


--
-- TOC entry 3581 (class 0 OID 0)
-- Dependencies: 242
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);


--
-- TOC entry 3582 (class 0 OID 0)
-- Dependencies: 240
-- Name: seq_nota_item_produto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_nota_item_produto', 1, false);


--
-- TOC entry 3583 (class 0 OID 0)
-- Dependencies: 217
-- Name: seq_pessoa; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);


--
-- TOC entry 3584 (class 0 OID 0)
-- Dependencies: 233
-- Name: seq_produto; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_produto', 1, false);


--
-- TOC entry 3585 (class 0 OID 0)
-- Dependencies: 234
-- Name: seq_status_rastreio; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);


--
-- TOC entry 3586 (class 0 OID 0)
-- Dependencies: 222
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_usuario', 1, false);


--
-- TOC entry 3587 (class 0 OID 0)
-- Dependencies: 243
-- Name: seq_vd_compra_loja; Type: SEQUENCE SET; Schema: public; Owner: edersonjseder
--

SELECT pg_catalog.setval('public.seq_vd_compra_loja', 1, false);


--
-- TOC entry 3312 (class 2606 OID 16406)
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 3348 (class 2606 OID 16619)
-- Name: avaliacao_produto avaliacao_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT avaliacao_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3310 (class 2606 OID 16400)
-- Name: categoria_produto categoria_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.categoria_produto
    ADD CONSTRAINT categoria_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3330 (class 2606 OID 16478)
-- Name: conta_pagar conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.conta_pagar
    ADD CONSTRAINT conta_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 3326 (class 2606 OID 16462)
-- Name: conta_receber conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id);


--
-- TOC entry 3332 (class 2606 OID 16484)
-- Name: cupom_desconto cupom_desconto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.cupom_desconto
    ADD CONSTRAINT cupom_desconto_pkey PRIMARY KEY (id);


--
-- TOC entry 3318 (class 2606 OID 16429)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 3328 (class 2606 OID 16470)
-- Name: forma_pagamento forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id);


--
-- TOC entry 3338 (class 2606 OID 16509)
-- Name: imagem_produto imagem_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT imagem_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3346 (class 2606 OID 16603)
-- Name: item_venda_loja item_venda_loja_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT item_venda_loja_pkey PRIMARY KEY (id);


--
-- TOC entry 3308 (class 2606 OID 16392)
-- Name: marca_produto marca_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.marca_produto
    ADD CONSTRAINT marca_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3340 (class 2606 OID 16522)
-- Name: nota_fiscal_compra nota_fiscal_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT nota_fiscal_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 3344 (class 2606 OID 16556)
-- Name: nota_fiscal_venda nota_fiscal_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT nota_fiscal_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 3342 (class 2606 OID 16527)
-- Name: nota_item_produto nota_item_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_item_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3314 (class 2606 OID 16413)
-- Name: pessoa_fisica pessoa_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);


--
-- TOC entry 3316 (class 2606 OID 16420)
-- Name: pessoa_juridica pessoa_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);


--
-- TOC entry 3334 (class 2606 OID 16492)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 3336 (class 2606 OID 16499)
-- Name: status_rastreio status_rastreio_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT status_rastreio_pkey PRIMARY KEY (id);


--
-- TOC entry 3322 (class 2606 OID 16464)
-- Name: usuarios_acesso uk_8bak9jswon2id2jbunuqlfl9e; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT uk_8bak9jswon2id2jbunuqlfl9e UNIQUE (acesso_id);


--
-- TOC entry 3324 (class 2606 OID 16444)
-- Name: usuarios_acesso unique_acesso_usuario; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT unique_acesso_usuario UNIQUE (usuario_id, acesso_id);


--
-- TOC entry 3320 (class 2606 OID 16437)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3350 (class 2606 OID 16630)
-- Name: vd_compra_loja vd_compra_loja_pkey; Type: CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.vd_compra_loja
    ADD CONSTRAINT vd_compra_loja_pkey PRIMARY KEY (id);


--
-- TOC entry 3380 (class 2620 OID 16672)
-- Name: avaliacao_produto validachavepessoaavaliacaoproduto; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoaavaliacaoproduto BEFORE UPDATE ON public.avaliacao_produto FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3379 (class 2620 OID 16673)
-- Name: avaliacao_produto validachavepessoaavaliacaoprodutoinsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoaavaliacaoprodutoinsert BEFORE INSERT ON public.avaliacao_produto FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3376 (class 2620 OID 16675)
-- Name: conta_pagar validachavepessoacontapagar; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoacontapagar BEFORE UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3374 (class 2620 OID 16677)
-- Name: conta_pagar validachavepessoacontapagarfunc; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoacontapagarfunc BEFORE UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoafunc();


--
-- TOC entry 3373 (class 2620 OID 16678)
-- Name: conta_pagar validachavepessoacontapagarfuncinsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoacontapagarfuncinsert BEFORE INSERT ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoafunc();


--
-- TOC entry 3375 (class 2620 OID 16676)
-- Name: conta_pagar validachavepessoacontapagarinsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoacontapagarinsert BEFORE INSERT ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3372 (class 2620 OID 16697)
-- Name: conta_receber validachavepessoacontareceber; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoacontareceber BEFORE UPDATE ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3371 (class 2620 OID 16698)
-- Name: conta_receber validachavepessoacontareceberinsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoacontareceberinsert BEFORE INSERT ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3368 (class 2620 OID 16695)
-- Name: endereco validachavepessoaendereco; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoaendereco BEFORE UPDATE ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3367 (class 2620 OID 16696)
-- Name: endereco validachavepessoaenderecoinsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoaenderecoinsert BEFORE INSERT ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3378 (class 2620 OID 16693)
-- Name: nota_fiscal_compra validachavepessoanotafiscalcompra; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoanotafiscalcompra BEFORE UPDATE ON public.nota_fiscal_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3377 (class 2620 OID 16694)
-- Name: nota_fiscal_compra validachavepessoanotafiscalcomprainsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoanotafiscalcomprainsert BEFORE INSERT ON public.nota_fiscal_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3370 (class 2620 OID 16691)
-- Name: usuario validachavepessoausuario; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoausuario BEFORE UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3369 (class 2620 OID 16692)
-- Name: usuario validachavepessoausuarioinsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoausuarioinsert BEFORE INSERT ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3381 (class 2620 OID 16689)
-- Name: vd_compra_loja validachavepessoavdcompraloja; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoavdcompraloja BEFORE UPDATE ON public.vd_compra_loja FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3382 (class 2620 OID 16690)
-- Name: vd_compra_loja validachavepessoavdcompralojainsert; Type: TRIGGER; Schema: public; Owner: edersonjseder
--

CREATE TRIGGER validachavepessoavdcompralojainsert BEFORE INSERT ON public.vd_compra_loja FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 3351 (class 2606 OID 16446)
-- Name: usuarios_acesso acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT acesso_fk FOREIGN KEY (acesso_id) REFERENCES public.acesso(id);


--
-- TOC entry 3355 (class 2606 OID 16540)
-- Name: nota_fiscal_compra conta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT conta_pagar_fk FOREIGN KEY (conta_pagar_id) REFERENCES public.conta_pagar(id);


--
-- TOC entry 3362 (class 2606 OID 16646)
-- Name: vd_compra_loja cupom_desconto_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.vd_compra_loja
    ADD CONSTRAINT cupom_desconto_fk FOREIGN KEY (cupom_desconto_id) REFERENCES public.cupom_desconto(id);


--
-- TOC entry 3363 (class 2606 OID 16651)
-- Name: vd_compra_loja endereco_cobranca_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.vd_compra_loja
    ADD CONSTRAINT endereco_cobranca_fk FOREIGN KEY (endereco_cobranca_id) REFERENCES public.endereco(id);


--
-- TOC entry 3364 (class 2606 OID 16656)
-- Name: vd_compra_loja endereco_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.vd_compra_loja
    ADD CONSTRAINT endereco_entrega_fk FOREIGN KEY (endereco_entrega_id) REFERENCES public.endereco(id);


--
-- TOC entry 3365 (class 2606 OID 16661)
-- Name: vd_compra_loja forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.vd_compra_loja
    ADD CONSTRAINT forma_pagamento_fk FOREIGN KEY (forma_pagamento_id) REFERENCES public.forma_pagamento(id);


--
-- TOC entry 3356 (class 2606 OID 16535)
-- Name: nota_item_produto nota_fiscal_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_fiscal_compra_fk FOREIGN KEY (nota_fiscal_compra_id) REFERENCES public.nota_fiscal_compra(id);


--
-- TOC entry 3366 (class 2606 OID 16666)
-- Name: vd_compra_loja nota_fiscal_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.vd_compra_loja
    ADD CONSTRAINT nota_fiscal_venda_fk FOREIGN KEY (nota_fiscal_venda_id) REFERENCES public.nota_fiscal_venda(id);


--
-- TOC entry 3354 (class 2606 OID 16511)
-- Name: imagem_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3357 (class 2606 OID 16545)
-- Name: nota_item_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3359 (class 2606 OID 16605)
-- Name: item_venda_loja produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3361 (class 2606 OID 16621)
-- Name: avaliacao_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 3352 (class 2606 OID 16451)
-- Name: usuarios_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 3360 (class 2606 OID 16631)
-- Name: item_venda_loja venda_compra_loja_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT venda_compra_loja_fk FOREIGN KEY (venda_compra_loja_id) REFERENCES public.vd_compra_loja(id);


--
-- TOC entry 3358 (class 2606 OID 16636)
-- Name: nota_fiscal_venda venda_compra_loja_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT venda_compra_loja_fk FOREIGN KEY (venda_compra_loja_id) REFERENCES public.vd_compra_loja(id);


--
-- TOC entry 3353 (class 2606 OID 16641)
-- Name: status_rastreio venda_compra_loja_fk; Type: FK CONSTRAINT; Schema: public; Owner: edersonjseder
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT venda_compra_loja_fk FOREIGN KEY (venda_compra_loja_id) REFERENCES public.vd_compra_loja(id);


--
-- TOC entry 3568 (class 0 OID 0)
-- Dependencies: 3567
-- Name: DATABASE lojavirtual; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON DATABASE lojavirtual TO edersonjseder;


-- Completed on 2023-07-02 10:55:20 EDT

--
-- PostgreSQL database dump complete
--
