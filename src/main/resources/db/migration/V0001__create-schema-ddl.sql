CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.client (
     id INT,
     "name" varchar(20) NULL,
     "limit" INTEGER NOT NULL,
     balance INTEGER NOT NULL,
     CONSTRAINT account_pkey PRIMARY KEY (id ASC)
);

CREATE TABLE IF NOT EXISTS public.transaction (
     id SERIAL NOT NULL PRIMARY KEY,
     "description" VARCHAR(10) NOT NULL,
     "type" VARCHAR(1) NOT NULL,
     amount INTEGER NOT NULL,
     created_at timestamp NOT NULL,
     client_id INTEGER NOT NULL,
     CONSTRAINT account_id_fk FOREIGN KEY (client_id) REFERENCES public.client(id)
);
