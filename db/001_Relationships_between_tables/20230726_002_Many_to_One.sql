SET CLIENT_ENCODING TO 'WIN1251';

drop table if exists Error_logs;
drop table if exists Dict_errors;

create table Dict_errors(
    code int primary key,
    description varchar(1000)
);

create table Error_logs(
    id serial primary key,
    event_time timestamp,
    code int references Dict_errors(code)
);

insert into Dict_errors (code, description) values(400, 'Bad Request');
insert into Dict_errors (code, description) values(403, 'Forbidden');
insert into Dict_errors (code, description) values(502, 'Bad Gateway');
insert into Dict_errors (code, description) values(503, 'Service Unavailabl');

insert into Error_logs (event_time, code) values(current_timestamp, 400);
insert into Error_logs (event_time, code) values(current_timestamp + interval '1 second', 400);
insert into Error_logs (event_time, code) values(current_timestamp + interval '1 minute', 502);
insert into Error_logs (event_time, code) values(current_timestamp + interval '1 hours', 503);

select * from Dict_errors;
select * from Error_logs;
