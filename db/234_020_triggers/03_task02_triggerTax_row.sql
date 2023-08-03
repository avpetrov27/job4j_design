create or replace function tax()
    returns trigger as
$$
    BEGIN
        new.price = new.price * 1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create or replace trigger tax_trigger
    before insert
    on products
    for each row
    execute function tax();