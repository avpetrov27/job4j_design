create table car_bodies (
    id serial primary key,
    name varchar(255)
);

create table car_engines (
    id serial primary key,
    name varchar(255)
);

create table car_transmissions (
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values
    ('Hatchback'),
    ('Limousine'),
    ('Minivan'),
    ('Roadster'),
    ('Sedan');

insert into car_engines(name) values
    ('4s'),
    ('8s'),
    ('V6'),
    ('V8');

insert into car_transmissions(name) values
    ('Manual'),
    ('Automatic'),
    ('Robotic'),
    ('Variable');

insert into cars(name, body_id, engine_id, transmission_id) values
    ('A', 2, 2, 3),
    ('B', 3, 3, 1),
    ('C', 2, 2, 1),
    ('D', 3, 4, 2),
    ('E', 5, 3, 2),
    ('F', 5, 1, 3),
    ('G', 5, 2, 2),
    ('H', 4, 2, 3),
    ('X1', null, 4, 3),
    ('X2', 5, null, 3),
    ('X3', 3, 2, null);
