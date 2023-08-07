create or replace function f_delete_data(price_limit integer)
returns integer
language 'plpgsql'
as $$
    declare
        count_del integer;
    BEGIN
		select into count_del count(*) from products where price > price_limit;
		delete from products where price > price_limit;
		return count_del;
    END;
$$;