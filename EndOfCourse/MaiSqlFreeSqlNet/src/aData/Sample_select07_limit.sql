/* */

/* select with LIMIT sample 'A' */

SELECT 
    customerNumber, 
    customerName, 
    creditLimit
FROM
    customers
ORDER BY creditLimit DESC
LIMIT 5;

/* select with LIMIT sample 'B' */

SELECT 
    customerNumber, 
    customerName, 
    creditLimit
FROM
    customers
ORDER BY creditLimit
LIMIT 5;

/* select with LIMIT sample 'C' */

SELECT 
    customerNumber, 
    customerName, 
    creditLimit
FROM
    customers
ORDER BY 
    creditLimit, 
    customerNumber
LIMIT 5;

/* select with LIMIT sample 'D' - pagination */

SELECT 
    customerNumber, 
    customerName
FROM
    customers
ORDER BY customerName    
LIMIT 10;

------------

SELECT 
    customerNumber, 
    customerName
FROM
    customers
ORDER BY customerName    
LIMIT 10, 10;

/* select with LIMIT sample 'E' - the nth highest or lowest value */

SELECT select_list
FROM table_name
ORDER BY sort_expression
LIMIT n-1, 1;

/* select with LIMIT sample 'E' - the second-highest credit */

SELECT 
    customerName, 
    creditLimit
FROM
    customers
ORDER BY 
    creditLimit DESC    
LIMIT 1,1;


