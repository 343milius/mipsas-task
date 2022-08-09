ALTER SEQUENCE hibernate_sequence RESTART WITH 1;

INSERT INTO CUSTOMERS(id, address, company_code, company_name, name, personal_code, surname) VALUES (123, 'TestAddress', 456, 'TestCompanyName', 'TestName', 789, 'TestSurname');
INSERT INTO ORDERS (id, name, type, description, active_from, active_to, customer_id) VALUES (321, 'Name', 'Type', 'Description', '1999-09-09', '2009-09-09', 123);