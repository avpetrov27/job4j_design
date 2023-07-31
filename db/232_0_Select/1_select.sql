select * from students;

select name, course, speciality from students;

select * from students where course = 2;

select * from students where course != 2;

select * from students where name is null;

select * from students where name is not null;

select * from students where enroll_date >= '01.09.2020';

select * from students where course > 2;

select * from students where name like 'D%';

select * from students where name like 'D%' and course > 2;

select * from students where name like 'D%' or course > 2;

select current_date;

select current_date > '10.09.2020' as check;

select current_date + interval '1 month' as newDate;

select * from students order by speciality asc;

select * from students order by speciality desc;

select distinct course from students;

select * from students limit 5;