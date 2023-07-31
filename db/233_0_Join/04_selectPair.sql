select *
from teens t1
cross join teens t2
where t1.gender > t2.gender;