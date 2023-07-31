select *
from Authors A
join Books B on A.id_author = B.id_author;

select A.name_author, B.name_book
from Authors A
join Books B on A.id_author = B.id_author;

select A.name_author author, B.name_book book
from Authors A
join Books B on A.id_author = B.id_author;