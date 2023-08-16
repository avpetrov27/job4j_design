BEGIN;
    DECLARE cursor_products cursor for
        select * from products order by id;
    FETCH LAST FROM cursor_products;
    MOVE BACKWARD 6 FROM cursor_products;
    FETCH NEXT FROM cursor_products;
    MOVE BACKWARD 9 FROM cursor_products;
    FETCH NEXT FROM cursor_products;
    MOVE BACKWARD 6 FROM cursor_products;
    FETCH NEXT FROM cursor_products;
    FETCH PRIOR FROM cursor_products;
    CLOSE cursor_products;
COMMIT;
