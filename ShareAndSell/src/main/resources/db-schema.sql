create table if not exists users(
    id serial PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL UNIQUE ,
    password text NOT NULL,
    role text
);

create TABLE advertisements (
                                id SERIAL PRIMARY KEY,
                                title text,
                                description text,
                                quantity int,
                                price int,
                                seller_id int
);

