create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function history_of_price()
    returns trigger as
$$
    BEGIN
        insert into history_of_price(name, price, date) values (New.name, New.price, CURRENT_DATE);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create or replace trigger history_of_price_trigger
    after insert
    on products
    for each row
    execute function history_of_price();