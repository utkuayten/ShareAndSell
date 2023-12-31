create table if not exists users
(
    id    serial PRIMARY KEY,
    name  text NOT NULL,
    email text NOT NULL UNIQUE,
    password text NOT NULL,
    role  text
);

create TABLE if not exists advertisements
(
    id          SERIAL PRIMARY KEY,
    title       text,
    description text,
    quantity    int,
    price       int,
    seller_id   int references users (id)
);

create TABLE if not exists wallets
(
    id       SERIAL PRIMARY KEY,
    owner_id int references users (id),
    balance  int
);


create TABLE if not exists transactions
(
    id         SERIAL PRIMARY KEY,
    seller_id  int,
    buyer_id   int REFERENCES users (id),
    product_id int REFERENCES advertisements (id),
    price      int,
    quantity   int,
    status     text
);

create TABLE if not exists images
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) UNIQUE,
    type     varchar(30),
    owner_id int,
    imageData bytea,
    purpose   varchar(50)
);

CREATE TABLE IF NOT EXISTS basket
(
    id         SERIAL PRIMARY KEY,
    buyer_id   INT REFERENCES users (id),
    quantity   INT,
    product_id INT REFERENCES advertisements (id)
);
