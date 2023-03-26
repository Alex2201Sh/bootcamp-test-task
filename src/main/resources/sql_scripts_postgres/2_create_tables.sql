create table if not exists public.users
(
    id        bigserial
        constraint users_pk
            primary key,
    surname   varchar(40),
    name      varchar(20),
    paternity varchar(40),
    email     varchar(50),
    role      integer
);


