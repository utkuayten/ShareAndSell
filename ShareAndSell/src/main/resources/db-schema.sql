create table if not exists sample(
    id serial PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    data text,
    value int default 0
);

create table if not exists company(
     name VARCHAR(64) PRIMARY KEY,
     country text,
     streetInfo text,
     phoneNumber text UNIQUE NOT NULL
);

create table if not exists e_Mails(
    e_Mail text PRIMARY KEY,
    name VARCHAR(64),
    CONSTRAINT fk_name FOREIGN KEY(name) REFERENCES company(name)
);

create table if not exists city(
    zip int PRIMARY KEY ,
    city text
);

create table if not exists address(
    name VARCHAR(64) PRIMARY KEY,
    country text,
    zip int,
    streetInfo text,
    CONSTRAINT fk_compName FOREIGN KEY (name) REFERENCES company(name),
    CONSTRAINT fk_zip FOREIGN KEY (zip) REFERENCES city(zip)
);

create table if not exists product(
    id serial PRIMARY KEY,
    name varchar(64) NOT NULL,
    description text,
    brandName text
);

create table if not exists produce(
    produceId serial PRIMARY KEY,
    companyName VARCHAR(64),
    productId int,
    capacity int,
    CONSTRAINT fk_compName FOREIGN KEY (companyName) REFERENCES company(name),
    CONSTRAINT fk_proName FOREIGN KEY (productId) REFERENCES product(id)
);

create table if not exists transaction(
    transactionId serial PRIMARY KEY,
    companyName VARCHAR(64),
    productId int,
    amount int,
    date date,
    CONSTRAINT fk_compName FOREIGN KEY (companyName) REFERENCES company(name),
    CONSTRAINT fk_proName FOREIGN KEY (productId) REFERENCES product(id)
);

create FUNCTION sample_trigger() RETURNS TRIGGER AS
'
    BEGIN
        IF (SELECT value FROM sample where id = NEW.id ) > 1000
           THEN
           RAISE SQLSTATE ''23503'';
           END IF;
        RETURN NEW;
    END;
' LANGUAGE plpgsql;

create FUNCTION amount_capacity() RETURNS TRIGGER AS
'
    BEGIN
        IF((SELECT capacity FROM produce WHERE produce.companyName=NEW.companyName AND produceId=NEW.productId) < ((SELECT SUM(amount) FROM transaction WHERE transaction.companyName=NEW.companyName AND transaction.productId=NEW.productId)))
            THEN
            RAISE EXCEPTION ''Capacity exceeded'';
end if;
RETURN NEW;
end;
' LANGUAGE plpgsql;

/*create FUNCTION zip_city() RETURNS TRIGGER AS
'
    BEGIN
        IF (SELECT COUNT(city) FROM city WHERE zip=NEW.zip) == 2
            THEN
                DELETE FROM city WHERE zip=NEW.zip;
end if;
end;
' LANGUAGE plpgsql;*/


/*create FUNCTION zip_city_trigger() RETURNS TRIGGER AS
'
    BEGIN
        IF(SELECT city FROM city WHERE zip=NEW.zip) != NEW.city
        THEN
            RAISE EXCEPTION ''zip city exception'';
        END IF;
RETURN NEW;
end;
' LANGUAGE plpgsql;*/

create TRIGGER sample_value AFTER insert ON sample
    FOR EACH ROW EXECUTE PROCEDURE sample_trigger();

create TRIGGER capacity_amount AFTER INSERT ON transaction
    FOR EACH ROW EXECUTE PROCEDURE amount_capacity();

/*create TRIGGER city_zip AFTER INSERT ON city
    FOR EACH STATEMENT EXECUTE PROCEDURE zip_city();*/

/*create TRIGGER zip_city BEFORE insert ON city
    FOR EACH ROW EXECUTE PROCEDURE zip_city_trigger();*/

create TABLE sample_table();

create TABLE company_table();

create TABLE e_Mails_table();

create TABLE product_table();

create TABLE city_table();

create Table produce_table();

create Table transaction_table();