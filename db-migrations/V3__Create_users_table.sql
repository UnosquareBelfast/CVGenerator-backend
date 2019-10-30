CREATE SEQUENCE IF NOT EXISTS public.users_id_seq;
CREATE TABLE IF NOT EXISTS public.users (
	id integer NOT NULL DEFAULT nextval('users_id_seq'::regclass),
	first_name text NOT NULL,
	last_name text NOT NULL,
	email text NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER SEQUENCE users_id_seq
    OWNED BY users.id;