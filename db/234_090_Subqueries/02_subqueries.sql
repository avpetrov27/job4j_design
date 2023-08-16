select *
from customers
where age = (select min(age) from customers);

select *
from customers
where id not in (select distinct customer_id from orders);
