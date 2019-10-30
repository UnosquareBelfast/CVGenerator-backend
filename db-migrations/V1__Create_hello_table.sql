CREATE SEQUENCE IF NOT EXISTS public.hello_id_seq;
CREATE TABLE IF NOT EXISTS public.hello
(
	id integer NOT NULL DEFAULT nextval('hello_id_seq'::regclass),
	greeting text NOT NULL,
	CONSTRAINT hello_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER SEQUENCE hello_id_seq
    OWNED BY hello.id;