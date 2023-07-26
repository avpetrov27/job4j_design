create table Customers(
      id serial primary key,
      name varchar(255),
	  age numeric(3,0),
      gender varchar(1)
);

insert into Customers (name, age, gender) values ('Petrov', 34, 'M');

select * from Customers;

update Customers set name = 'Petrov A V';

select * from Customers;

delete from Customers;

select * from Customers;