SET CLIENT_ENCODING TO 'WIN1251';

drop table if exists Info;
drop table if exists Users;

create table Users(
    id serial primary key,
    code int unique
);

create table Info(
    code int primary key references Users(code),
    info varchar(1000)
);

insert into Users (code) values(100);
insert into Users (code) values(105);
insert into Users (code) values(110);

insert into Info (code, info) values(100, 'information is secret');
insert into Info (code, info) values(105, 'name: Petrov, age:34, address: Moscow');
insert into Info (code, info) values(110, 'name: Ivanov');

select * from Info;
select * from Users;