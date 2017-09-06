insert into person(id, firstName, lastName, birthDate, street, number, zip, city, version) values(1, 'John', 'Doe', '1970-01-01', 'Brighton Road', '27', '1000', 'Brussels', 1);
insert into person(id, firstName, lastName, birthDate, street, number, zip, city, version) values(2, 'Jane', 'Doe', '1970-01-02', 'Harper Lane', '8', '2845', 'London', 1);
insert into person(id, firstName, lastName, birthDate, street, number, zip, city, version) values(3, 'Tyler', 'Turner', '1983-07-15', 'Masonstreet', '72B', '5874', 'Winterfell', 1);
insert into person(id, firstName, lastName, birthDate, street, number, zip, city, version) values(4, 'Marcia', 'Jackson', '1975-03-28', 'Factory Lane', '100/7', '1578', 'Glasgow', 1);
insert into person(id, firstName, lastName, birthDate, street, number, zip, city, version) values(5, 'Allan', 'Wilson', '1964-12-28', 'Turingstreet', '1', '2547', 'Newcastle', 1);

insert into company(id, name, description, version) values(1, 'LuftHansa', 'Best company in the world', 1);
insert into company(id, name, description, version) values(2, 'Air France', 'Best French company in the world', 1);
insert into company(id, name, description, version) values(3, 'Air Corsica', null, 1);

insert into user(id, city, country, number, street, zip, birthDate, email, firstName, lastName, password, phoneNumber, role, version, company_id) values(1, 'Sint-Niklaas', 'Belgium', '12b', 'kerkstraat', '9100', '2000-10-10 12:12:12', 'joris@test.com', 'Joris', 'Boschmans', 'test123', '+324551332', 'CLIENT', 1, NULL);
insert into user(id, city, country, number, street, zip, birthDate, email, firstName, lastName, password, phoneNumber, role, version, company_id) values  (2, 'Gent', 'Belgium', '56', 'grote markt', '9000', '2000-10-10 13:12:12', 'sam@test.com', 'Sam', 'Vermeulen', 'pass456', '+3245645687482', 'CLIENT', 1, NULL);
insert into user(id, city, country, number, street, zip, birthDate, email, firstName, lastName, password, phoneNumber, role, version, company_id) values  (3, 'Brussel', 'Belgium', '125', 'boulevard de Mons', '1000', '1999-10-10 13:12:12', 'Jean@test.com', 'Jean', 'Claes', 'pass789', '+325446642', 'AIRLINE_EMPLOYEE', 1, 2);


insert into flight(id, name, departureTime, arrivalTime, company_id, version) values(1, 'AH17', '2017-03-03 12:12:30', '2017-03-03 12:12:30', 1, 1);
insert into flight(id, name, departureTime, arrivalTime, company_id, version) values(2, 'AB17', '2017-03-03 21:00:30', '2017-03-03 12:12:30', 2, 1);
insert into flight(id, name, departureTime, arrivalTime, company_id, version) values(3, 'BB17', '2017-03-03 21:00:30', '2016-03-03 12:12:30', 1, 1);

insert into availableseats(Flight_id, budgetClass, available) values (2, 2, 30);
insert into availableseats(Flight_id, budgetClass, available) values (2, 1, 3);
insert into availableseats(Flight_id, budgetClass, available) values (2, 0, 20);
insert into availableseats(Flight_id, budgetClass, available) values (1, 1, 5);
insert into availableseats(Flight_id, budgetClass, available) values (1, 2, 7);

insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (1, 'PAID', '1912-12-12 12:12:12', NULL, 12.21, 541.20, 'ENDORSEMENT', 1);
insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (2, 'RESERVED', '1912-12-12 12:12:12', 76.95, NULL, 754.21, 'CREDIT_CARD', 2);
insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (3, 'PAID', '1912-12-12 12:12:12', NULL, NULL, 1125.20, 'ENDORSEMENT', 3);
insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (4, 'PAYMENT_PENDING', '1912-12-12 12:12:12', 76.95, 88.20, 1754.21, 'CREDIT_CARD', 1);