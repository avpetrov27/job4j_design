SET CLIENT_ENCODING TO 'WIN1251';

drop table if exists OrderDetails;
drop table if exists Goods;
drop table if exists Orders;

create table Orders(
    id_order serial primary key,
    number varchar(255) unique,
    info varchar(255)
);

create table Goods(
    id_good serial primary key,
    code numeric(10,0) unique,
    name varchar(255)
);

create table OrderDetails(
    id_orderdetails serial primary key,
    number varchar(255) references Orders(number),
    code numeric(10,0) references Goods(code),
    quantity numeric(10,0)
);

insert into Goods (code, name) values (101, 'pen');
insert into Goods (code, name) values (102, 'pencil');
insert into Goods (code, name) values (201, 'notebook');

insert into Orders (number, info) values('A-100001', 'vip client');

insert into OrderDetails (number, code, quantity) values ('A-100001', 101, 10);
insert into OrderDetails (number, code, quantity) values ('A-100001', 102, 10);
insert into OrderDetails (number, code, quantity) values ('A-100001', 201, 20);

select * from Orders;
select * from Goods;
select * from OrderDetails;
