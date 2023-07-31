create table Authors(
    id_author serial primary key,
    name_author varchar(255)
);

create table Books(
    id_book serial primary key,
    name_book varchar(255),
    id_author int references Authors(id_author)
);
