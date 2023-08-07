create or replace procedure p_delete_data()
language 'plpgsql'
as $$
    BEGIN
		delete from products where price is null;
    END;
$$;