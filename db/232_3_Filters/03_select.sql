--1
select p.name
from product p
join type t on p.type_id = t.id
where t.name = 'СЫР';

--2
select p.name
from product p
where p.name like '%мороженое%';

--3
select p.name
from product p
where p.expired_date < current_date;

--4
select p.name
from product p
where p.price = (
	select max(p1.price)
	from product p1
)

--5
select t.name, count(*)
from product p
join type t on p.type_id = t.id
group by t.name;

--6
select p.name
from product p
join type t on p.type_id = t.id
where t.name in ('СЫР', 'МОЛОКО');

--7
select t.name
from product p
join type t on p.type_id = t.id
group by t.name
having count(*) < 10;

--8
select p.name, t.name
from product p
join type t on p.type_id = t.id;
