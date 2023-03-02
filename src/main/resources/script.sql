-- Table: public.booking

-- DROP TABLE IF EXISTS public.booking;

CREATE TABLE IF NOT EXISTS public.booking
(
    booking_id character varying COLLATE pg_catalog."default" NOT NULL,
    source character varying COLLATE pg_catalog."default",
    destination character varying COLLATE pg_catalog."default",
    date character varying COLLATE pg_catalog."default",
    passenger_name character varying COLLATE pg_catalog."default",
    passenger_age character varying COLLATE pg_catalog."default",
    passenger_gender character varying COLLATE pg_catalog."default"
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.booking
    OWNER to test_user;