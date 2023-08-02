create view show_students_with_books as
    with cte as (
        select s.name student,
            count(a.name) count_books,
            a.name author,
            row_number() OVER (PARTITION BY s.name order by count(a.name) desc, a.name) AS row_number
        from students as s
        join orders o on s.id = o.student_id
        join books b on o.book_id = b.id
        join authors a on b.author_id = a.id
        group by (s.name, a.name)
    )
    select t1.student,
        sum(t1.count_books) count_books,
        count(t1.author) count_authors,
        string_agg(t1.author, ';') list_authors,
        (select author from cte t2 where t1.student = t2.student and row_number = 1) favorite_writer,
        max(count_books) max_books_of_favorite_writer
    from cte t1
    group by t1.student
    order by t1.student