create table if not exists userDB
(
    id       serial  primary key ,
    name     text,
    mail     text unique,
    password text,
    role     text
);
