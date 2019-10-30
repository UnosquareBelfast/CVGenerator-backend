CREATE SEQUENCE IF NOT EXISTS public.templates_id_seq;
CREATE TABLE IF NOT EXISTS public.templates (
	id integer NOT NULL DEFAULT nextval('templates_id_seq'::regclass),
	template_name varchar(100) NOT NULL,
	template_html text NOT NULL,
	CONSTRAINT templates_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER SEQUENCE templates_id_seq
    OWNED BY templates.id;