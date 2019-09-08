/* */

/* select with ORDER BY sample 'A' */

SELECT
    contactLastname,
    contactFirstname
FROM
    customers
ORDER BY
    contactLastname;

/* select with ORDER BY sample 'B' */

SELECT
    contactLastname,
    contactFirstname
FROM
    customers
ORDER BY
    contactLastname DESC;

/* select with ORDER BY sample 'C' */

SELECT
    contactLastname,
    contactFirstname
FROM
    customers
ORDER BY
    contactLastname DESC,
    contactFirstname ASC;

/* select with ORDER BY sample 'D1' */

SELECT 
    orderNumber, 
    orderlinenumber, 
    quantityOrdered * priceEach
FROM
    orderdetails
ORDER BY 
   quantityOrdered * priceEach DESC;

/* select with ORDER BY sample 'D2' */

SELECT 
    orderNumber,
    orderLineNumber,
    quantityOrdered * priceEach AS subtotal
FROM
    orderdetails
ORDER BY subtotal DESC;

/* select with ORDER BY & FIELD() sample */

SELECT 
    orderNumber, 
    status
FROM
    orders
ORDER BY 
    FIELD(status,
        'In Process',
        'On Hold',
        'Cancelled',
        'Resolved',
        'Disputed',
        'Shipped');



