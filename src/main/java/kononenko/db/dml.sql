DELETE
FROM role;

DELETE
FROM author;

DELETE
FROM "user";

DELETE
FROM book;

DELETE
FROM book_author;

DELETE
FROM book_count;

DELETE
FROM book_order;

DELETE
FROM book_publish;

DELETE
FROM "order";

INSERT INTO role (name)
VALUES ('admin'),
       ('client');


INSERT INTO author (id, author_name, date_of_birth)
VALUES (1, 'Lev', '18280828'),
       (2, 'Jane', '17751216'),
       (3, 'Philip', '19461019');


INSERT INTO "user" (id, name, role_id)
VALUES (1, 'Alex', 1),
       (2, 'John', 2),
       (3, 'Jason', 2);


INSERT INTO book (id, title)
VALUES (1, 'War and Peace'),
       (2, 'Pride and Prejudice'),
       (3, 'Dark Beginnings');


INSERT INTO book_publish (id, book_id, year_of_publish)
VALUES (1, 1, 1867),
       (2, 2, 1813),
       (3, 3, 1995);


INSERT INTO book_count (id, book_publish_id, count)
VALUES (1, 1, 3),
       (2, 2, 3),
       (3, 3, 3);


INSERT INTO "order" (id, order_date, user_id)
VALUES (1, '20220710', 1),
       (2, '20220810', 2),
       (3, '20220910', 3);

INSERT INTO book_order (id, book_publish_id, order_id, cost, count)
VALUES (1, 1, 1, 300, 3),
       (2, 2, 2, 600, 3),
       (3, 3, 3, 800, 3);

INSERT INTO book_author (id, book_publish_id, author_id)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);