insert into Roles(role_name, role_description) values ('Admin', null);
insert into Roles(role_name, role_description) values ('Developer', null);
insert into Roles(role_name, role_description) values ('SecurityAdmin', null);
insert into Roles(role_name, role_description) values ('Manager', 'Performer on item');
insert into Roles(role_name, role_description) values ('User', 'Client');
insert into Roles(role_name, role_description) values ('VIP-User', 'Very-very client');

insert into Users(username, user_add_info, id_role) values ('Petrov', 'programmer', (select id_role from Roles where role_name = 'Developer'));
insert into Users(username, user_add_info, id_role) values ('Leonov', 'admin', (select id_role from Roles where role_name = 'Admin'));
insert into Users(username, user_add_info, id_role) values ('Smirnov', 'manager', (select id_role from Roles where role_name = 'Manager'));
insert into Users(username, user_add_info, id_role) values ('X', 'security', (select id_role from Roles where role_name = 'SecurityAdmin'));
insert into Users(username, user_add_info, id_role) values ('Ivanov', 'VIP', (select id_role from Roles where role_name = 'VIP-User'));
insert into Users(username, user_add_info, id_role) values ('Sidorov', 'user', (select id_role from Roles where role_name = 'User'));
insert into Users(username, user_add_info, id_role) values ('Sergeev', 'user', (select id_role from Roles where role_name = 'User'));
insert into Users(username, user_add_info, id_role) values ('Danilov', 'user', (select id_role from Roles where role_name = 'User'));
insert into Users(username, user_add_info, id_role) values ('Popov', null, null);

insert into Rules(rule_name, rule_description) values ('All', 'Full');
insert into Rules(rule_name, rule_description) values ('Creating Roles and Rules', 'Create, edit, definition roles and rules');
insert into Rules(rule_name, rule_description) values ('Creating Categories and States', 'Create, edit categories and states');
insert into Rules(rule_name, rule_description) values ('Creating Item', 'Create Item');
insert into Rules(rule_name, rule_description) values ('Reading Item', 'Only read, include comments and attach');
insert into Rules(rule_name, rule_description) values ('Editing Item', 'Read and edit, add comments and attach, edit states and categories');
insert into Rules(rule_name, rule_description) values ('Showing Lists Users, Roles and Rules', 'For Security');

insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'Admin'), (select id_rule from Rules where rule_name = 'Creating Roles and Rules'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'Developer'), (select id_rule from Rules where rule_name = 'All'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'SecurityAdmin'), (select id_rule from Rules where rule_name = 'Showing Lists Users, Roles and Rules'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'Manager'), (select id_rule from Rules where rule_name = 'Creating Categories and States'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'Manager'), (select id_rule from Rules where rule_name = 'Creating Item'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'Manager'), (select id_rule from Rules where rule_name = 'Reading Item'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'Manager'), (select id_rule from Rules where rule_name = 'Editing Item'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'User'), (select id_rule from Rules where rule_name = 'Creating Item'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'User'), (select id_rule from Rules where rule_name = 'Reading Item'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'VIP-User'), (select id_rule from Rules where rule_name = 'Creating Item'));
insert into Roles_Rules(id_role, id_rule) values ((select id_role from Roles where role_name = 'VIP-User'), (select id_rule from Rules where rule_name = 'Reading Item'));

insert into Categories(category, category_description) values ('A', 'Very Important');
insert into Categories(category, category_description) values ('B', 'Middle Important');
insert into Categories(category, category_description) values ('C', 'Low Important');

insert into States(state, state_description) values ('01_Start', null);
insert into States(state, state_description) values ('02_Working', null);
insert into States(state, state_description) values ('03_Finish', null);
insert into States(state, state_description) values ('99_Canceled', 'Canceled, terminated ant etc');

insert into Items(item_number, item_description, id_user, id_category, id_state) values (
	'A-10000001', 
	'first case', 
	(select id_role from Users where username = 'Ivanov'), 
	(select id_category from Categories where category = 'A'), 
	(select id_state from States where state = '02_Working')
);
insert into Items(item_number, item_description, id_user, id_category, id_state) values (
	'P-10000101', 
	'standard', 
	(select id_role from Users where username = 'Sidorov'), 
	(select id_category from Categories where category = 'B'), 
	(select id_state from States where state = '01_Start')
);
insert into Items(item_number, item_description, id_user, id_category, id_state) values (
	'Q-10000255', 
	'standard', 
	(select id_role from Users where username = 'Sergeev'), 
	(select id_category from Categories where category = 'B'), 
	(select id_state from States where state = '01_Start')
);

insert into Comments(comment, id_item) values ('abracadabra', (select id_item from Items where item_number = 'A-10000001'));

insert into Attachs(attach, id_item) values ('\100\100\100\100', (select id_item from Items where item_number = 'A-10000001'));
