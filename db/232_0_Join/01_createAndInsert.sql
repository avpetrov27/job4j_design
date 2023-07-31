create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('DAD');
insert into departments(name) values ('DD');
insert into departments(name) values ('DF');
insert into departments(name) values ('DE');

insert into employees(name, department_id) values('Boris', 1);
insert into employees(name, department_id) values('Ivan', 2);
insert into employees(name, department_id) values('Kirill', 2);
insert into employees(name, department_id) values ('Marina', 3);
insert into employees(name, department_id) values ('Maxim', 3);
insert into employees(name, department_id) values ('X', null);
