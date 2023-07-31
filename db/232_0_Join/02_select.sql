--2
select *
from departments d
left join employees e on d.id = e.department_id;

select *
from departments d
right join employees e on d.id = e.department_id;

select *
from departments d
full join employees e on d.id = e.department_id;

select *
from departments d
cross join employees e;

--3
select d.name department
from departments d
left join employees e on d.id = e.department_id
where e.name is null;

--4
select *
from departments d
left join employees e on d.id = e.department_id
where (d.name is not null and e.name is not null);

select *
from departments d
right join employees e on d.id = e.department_id
where (d.name is not null and e.name is not null);