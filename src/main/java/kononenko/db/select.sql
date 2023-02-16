SELECT *
FROM role
ORDER BY name DESC;

SELECT *
FROM role
WHERE name LIKE '%adm%';

SELECT *
FROM role
WHERE name LIKE 'c_____';

SELECT *
FROM author
WHERE author_name = 'Lev';

SELECT *
FROM author
WHERE author_name IN ('Jane');

SELECT *
FROM author
WHERE author_name NOT IN ('Lev', 'Jane');

SELECT *
FROM author
WHERE author_name = 'Jane'
  AND id > 1;

SELECT *
FROM author
WHERE author_name IN ('Jane')
   OR id > 2;

SELECT *
FROM role
ORDER BY name;

SELECT *
FROM role
ORDER BY name DESC;

SELECT name AS newcol
FROM "user"
UNION
SELECT title
FROM book;

SELECT id
FROM author
WHERE author_name LIKE 'L%'
UNION
SELECT id
FROM author
WHERE date_of_birth = '19461019'
UNION
SELECT id
FROM author
WHERE date_of_birth = '19461019'
ORDER BY id;


SELECT DISTINCT title
FROM book;

SELECT *
FROM "user" AS U
         INNER JOIN role AS R
                    ON U.role_id = R.id;

SELECT *
FROM "user" AS U
         LEFT JOIN role AS R
                   ON U.role_id = R.id
                       AND R.id = '1';

SELECT *
FROM book AS B
         RIGHT JOIN author AS A
                    ON B.title = A.author_name;
--AND R.id = '1';

SELECT COUNT(id)
FROM "user";

SELECT SUM(id)
FROM "user";

SELECT ABS(id)
FROM "user";

SELECT MAX(id)
FROM role;

SELECT AVG(id)
FROM role;

SELECT R.name, COUNT(U.id) AS user_count
FROM "user" AS U
         INNER JOIN role AS R
                    ON U.role_id = R.id
GROUP BY R.name
HAVING R.name LIKE 'c%';
