select title
from book
intersect
select name
from movie;

select title
from book
except
select name
from movie;

(select title
from book
except
select name
from movie)
union
(select name
from movie
except
select title
from book);
