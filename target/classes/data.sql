/**
 * CREATE Script for initialising of DB
 */

-- Create 3 Customers

insert into customer (id, date_created, customer_name, customer_balance, customer_currency) values (1, now(), 'rakesh',
0, 'EUR');
insert into customer (id, date_created, customer_name, customer_balance, customer_currency) values (2, now(), 'rahul',
0, 'EUR');
insert into customer (id, date_created, customer_name, customer_balance, customer_currency) values (3, now(), 'ramesh',
0, 'EUR');