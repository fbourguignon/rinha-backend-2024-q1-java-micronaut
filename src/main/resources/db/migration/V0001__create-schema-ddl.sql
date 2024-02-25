CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS public.client (
     id INT PRIMARY KEY,
     "name" varchar(20) NULL,
     "limit" INTEGER NOT NULL,
     balance INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS public.transaction (
     id UUID NOT NULL PRIMARY KEY,
     "description" VARCHAR(10) NOT NULL,
     "type" VARCHAR(1) NOT NULL,
     amount INTEGER NOT NULL,
     created_at timestamp NOT NULL,
     client_id INTEGER NOT NULL,
     CONSTRAINT account_id_fk FOREIGN KEY (client_id) REFERENCES public.client(id)
);
CREATE INDEX ON public.transaction (client_id, created_at);
