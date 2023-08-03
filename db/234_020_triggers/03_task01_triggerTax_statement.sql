create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create or replace trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute function tax();