DROP TABLE IF EXISTS book_order;
DROP TABLE IF EXISTS book_count;
DROP TABLE IF EXISTS book_author;
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS book_publish;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS "user";
DROP TABLE IF EXISTS role;

CREATE TABLE IF NOT EXISTS role
(
    id   integer NOT NULL AUTO_INCREMENT,
    name text    NOT NULL,
    CONSTRAINT "role_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "user"
(
    id      integer NOT NULL,
    name    text    NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT "user_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS book
(
    id    integer NOT NULL,
    title text    NOT NULL,
    CONSTRAINT "book_pkey" PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS "user"
    ADD CONSTRAINT "FK_user_role" FOREIGN KEY (role_id)
        REFERENCES role (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE;


CREATE TABLE IF NOT EXISTS "order"
(
    id         integer NOT NULL,
    order_date date    NOT NULL,
    user_id    integer NOT NULL,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_order_user" FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS book_publish
(
    id              integer NOT NULL,
    book_id         integer NOT NULL,
    year_of_publish integer NOT NULL,
    CONSTRAINT book_publish_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_book_publish_book" FOREIGN KEY (book_id)
        REFERENCES book (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS book_order
(
    id              integer,
    book_publish_id integer NOT NULL,
    order_id        integer NOT NULL,
    cost            integer NOT NULL,
    count           integer NOT NULL,
    CONSTRAINT book_order_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_book_order_order" FOREIGN KEY (order_id)
        REFERENCES "order" (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "FK_book_publish_book_order" FOREIGN KEY (book_publish_id)
        REFERENCES book_publish (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS book_count
(
    id              integer NOT NULL,
    book_publish_id integer NOT NULL,
    count           integer NOT NULL,
    CONSTRAINT book_count_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_book_count_book_publish" FOREIGN KEY (book_publish_id)
        REFERENCES book_publish (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS author
(
    id            integer NOT NULL,
    author_name   text    NOT NULL,
    date_of_birth date    NOT NULL,
    CONSTRAINT author_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS book_author
(
    id              integer NOT NULL,
    book_publish_id integer NOT NULL,
    author_id       integer NOT NULL,
    CONSTRAINT book_author_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_book_author_author" FOREIGN KEY (author_id)
        REFERENCES author (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT "FK_book_author_book_publish" FOREIGN KEY (book_publish_id)
        REFERENCES book_publish (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
