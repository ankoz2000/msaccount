CREATE TABLE public.account
(
    id serial NOT NULL,
    "accountCode" integer NOT NULL,
    balance money NOT NULL DEFAULT 100,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.account
    OWNER to postgres;