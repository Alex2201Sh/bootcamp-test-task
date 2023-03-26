CREATE TABLE IF NOT EXISTS public.users
(
    id        INTEGER AUTO_INCREMENT PRIMARY KEY,
    surname   VARCHAR(40),
    name      VARCHAR(20),
    paternity VARCHAR(40),
    email     VARCHAR(50),
    role      INTEGER
);


