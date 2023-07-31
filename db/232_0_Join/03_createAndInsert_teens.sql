create table teens (
    id serial primary key,
    name varchar(255),
    gender varchar(1)
);

insert into teens(name, gender) values('Boris', 'M');
insert into teens(name, gender) values('Marina', 'F');
insert into teens(name, gender) values('Polina', 'F');
insert into teens(name, gender) values('Ivan', 'M');
insert into teens(name, gender) values('Kirill', 'M');