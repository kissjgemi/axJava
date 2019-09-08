/* */

/* select with GROUP BY sample 'A' */

SELECT
    status
FROM
    orders
GROUP BY status;

/* select with GROUP BY sample 'B' */

SELECT DISTINCT
    status
FROM
    orders;

/* select with GROUP BY & COUNT sample */

SELECT
    status, COUNT(*)
FROM
    orders
GROUP BY status;

/* select with GROUP BY & SUM sample 'A' */

SELECT
    status, SUM(quantityOrdered * priceEach) AS amount
FROM
    orders
        INNER JOIN
    orderdetails USING (orderNumber)
GROUP BY status;

/* select with GROUP BY & SUM sample 'B' */

SELECT
    orderNumber,
    SUM(quantityOrdered * priceEach) AS total
FROM
    orderdetails
GROUP BY orderNumber;

/* select with GROUP BY & expression sample */

SELECT
    YEAR(orderDate) AS year,
    SUM(quantityOrdered * priceEach) AS total
FROM
    orders
        INNER JOIN
    orderdetails USING (orderNumber)
WHERE
    status = 'Shipped'
GROUP BY YEAR(orderDate);

/* select with GROUP BY & HAVING sample */

SELECT
    YEAR(orderDate) AS year,
    SUM(quantityOrdered * priceEach) AS total
FROM
    orders
        INNER JOIN
    orderdetails USING (orderNumber)
WHERE
    status = 'Shipped'
GROUP BY year
HAVING year > 2003;


