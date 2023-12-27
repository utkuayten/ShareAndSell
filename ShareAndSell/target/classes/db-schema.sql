create table if not exists users(
    id serial PRIMARY KEY,
    name text NOT NULL,
    email text NOT NULL UNIQUE ,
    password text NOT NULL,
    role text
);

CREATE TABLE advertisements (
    id SERIAL PRIMARY KEY,
    seller_id BIGINT NOT NULL,
    image_data BYTEA
);
