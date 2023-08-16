create table customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values 
							('Alexander', 'Petrov', 34, 'Russia'),
							('Ivan', 'Ivanov', 38, 'Russia'),
							('X', 'X', 999, 'Traziland');

create table orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values 
										(100, 1),
										(200, 1),
										(500, 2);
