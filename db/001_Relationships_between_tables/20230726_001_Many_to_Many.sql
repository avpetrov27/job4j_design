SET CLIENT_ENCODING TO 'WIN1251';

drop table if exists OrderDetails;
drop table if exists Order_Goods;
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

create table Order_Goods(
    id_orderdetails serial primary key,
    number varchar(255) references Orders(number),
    code numeric(10,0) references Goods(code)
);

create table OrderDetails(
    id_orderdetails int primary key references Order_Goods(id_orderdetails) ,
    quantity numeric(10,0),
    discount numeric(2,0),
    price_per_unit numeric(10,0),
    discount_price_per_unit numeric(10,0)
);

insert into Goods (code, name) values (101, 'pen');
insert into Goods (code, name) values (102, 'pencil');
insert into Goods (code, name) values (201, 'notebook');

insert into Orders (number, info) values('A-100001', 'vip client');

insert into Order_Goods (number, code) values ('A-100001', 101);
insert into Order_Goods (number, code) values ('A-100001', 102);
insert into Order_Goods (number, code) values ('A-100001', 201);

insert into OrderDetails (id_orderdetails, quantity, discount, price_per_unit, discount_price_per_unit) values (1, 10, 0, 100, 100);
insert into OrderDetails (id_orderdetails, quantity, discount, price_per_unit, discount_price_per_unit) values (2, 10, 10, 100, 90);
insert into OrderDetails (id_orderdetails, quantity, discount, price_per_unit, discount_price_per_unit) values (3, 20, 5, 200, 190);

select * from Orders;
select * from Goods;
select * from Order_Goods;
select * from OrderDetails;
