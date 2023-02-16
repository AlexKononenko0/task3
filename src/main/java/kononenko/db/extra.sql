DROP INDEX IF EXISTS IX_author_author_name;

CREATE UNIQUE INDEX IX_author_author_name
    ON author
        (author_name ASC NULLS LAST)
;

DROP INDEX IF EXISTS IX_book_title;

CREATE UNIQUE INDEX IX_book_title
    ON book
        (title ASC NULLS LAST)
;

DROP SEQUENCE IF EXISTS book_sequence;

CREATE SEQUENCE book_sequence
    MINVALUE 1
    MAXVALUE 999999999
    START WITH 4
    INCREMENT BY 1
    CACHE 20;

--SELECT nextval('book_sequence');

INSERT INTO book
    (id, title)
VALUES (nextval('book_sequence'), 'Lord of the Rings');

SELECT *
FROM book;

DELETE
FROM book
WHERE title = 'Lord of the Rings';

DROP VIEW IF EXISTS vw_book_author;

CREATE VIEW vw_book_author
AS
SELECT B.title, BP.year_of_publish, A.author_name, A.date_of_birth
FROM book AS B
         INNER JOIN book_publish AS BP
                    ON BP.book_id = B.id
         INNER JOIN book_author AS BA
                    ON BA.book_publish_id = BP.id
         INNER JOIN author AS A
                    ON BA.author_id = A.id;

SELECT *
FROM vw_book_author;

DROP VIEW IF EXISTS vw_admins;

CREATE VIEW vw_admins
AS
SELECT *
FROM "user" AS U
WHERE U.role_id = '1';

SELECT *
FROM vw_admins;