create table Roles (
    id_role serial primary key,
    role_name text unique not null,
	role_description text
);

create table Users (
    id_user serial primary key,
    username text not null,
    user_add_info text,
    id_role int references Roles(id_role)
);

create table Rules (
    id_rule serial primary key,
    rule_name text unique not null,
	rule_description text
);

create table Roles_Rules (
    id serial primary key,
    id_role int references Roles(id_role) not null,
    id_rule int references Rules(id_rule) not null
);

create table Categories (
    id_category serial primary key,
    category text unique not null,
	category_description text
);

create table States (
    id_state serial primary key,
    state text unique not null,
	state_description text
);

create table Items (
    id_item serial primary key,
    item_number text not null,
	item_description text,
    id_user int references Users(id_user) not null,
	id_category int references Categories(id_category),
	id_state int references States(id_state)
);

create table Comments (
    id_comment serial primary key,
    comment text not null,
    id_item int references Items(id_item) not null
);

create table Attachs (
    id_attach serial primary key,
    attach bytea not null,
    id_item int references Items(id_item) not null
);
