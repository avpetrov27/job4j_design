--1
select C.id, C.name car_name, CB.name body_name, CE.name engine_name, CT.name transmission_name
from cars C
left join car_bodies CB on C.body_id = CB.id
left join car_engines CE on C.engine_id = CE.id
left join car_transmissions CT on C.transmission_id = CT.id;

--2
select CB.name
from car_bodies CB
left join cars c on C.body_id = CB.id
where C.id is null;

--3
select CE.name
from car_engines CE
left join cars c on C.engine_id = CE.id
where C.id is null;

--4
select CT.name
from car_transmissions CT
left join cars c on C.transmission_id = CT.id
where C.id is null;
