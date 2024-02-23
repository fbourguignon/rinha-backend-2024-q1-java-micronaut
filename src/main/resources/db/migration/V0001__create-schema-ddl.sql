CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE public.account (
     id SERIAL PRIMARY KEY,
     "name" varchar(20) NULL,
     "limit" INTEGER NOT NULL,
     balance INTEGER NOT NULL
);

CREATE TABLE public.transaction (
     id SERIAL PRIMARY KEY,
     "description" VARCHAR(10) NOT NULL,
     "type" VARCHAR(1) NOT NULL,
     amount INTEGER NOT NULL,
     account_id INTEGER NOT NULL,
     CONSTRAINT account_id_fk FOREIGN KEY (account_id) REFERENCES public.account(id)
);
