insert into company(id, name, description, version) values(1, 'LuftHansa', 'Best company in the world', 1);
insert into company(id, name, description, version) values(2, 'Air France', 'Best French company in the world', 1);
insert into company(id, name, description, version) values(3, 'Air Corsica', null, 1);

insert into user(id, city, country, number, street, zip, birthDate, email, firstName, lastName, password, phoneNumber, role, version, company_id) values(1, 'Sint-Niklaas', 'Belgium', '12b', 'kerkstraat', '9100', '2000-10-10 12:12:12', 'joris@test.com', 'Joris', 'Boschmans', '$2a$13$ARYIYpKiT/wWNfiKl5FcG.WxnSVt6hseUudKq9oNFwvkWIPiuy7XO', '+324551332', 'CLIENT', 1, NULL);
insert into user(id, city, country, number, street, zip, birthDate, email, firstName, lastName, password, phoneNumber, role, version, company_id) values  (2, 'Gent', 'Belgium', '56', 'grote markt', '9000', '2000-10-10 13:12:12', 'sam@test.com', 'Sam', 'Vermeulen', 'pass456', '+3245645687482', 'CLIENT', 1, NULL);
insert into user(id, city, country, number, street, zip, birthDate, email, firstName, lastName, password, phoneNumber, role, version, company_id) values  (3, 'Brussel', 'Belgium', '125', 'boulevard de Mons', '1000', '1999-10-10 13:12:12', 'Jean@test.com', 'Jean', 'Claes', 'pass789', '+325446642', 'AIRLINE_EMPLOYEE', 1, 2);

insert into airport(id, city, code, country, name, region, version) VALUES (1, 'Brussels', 'ZAV', 'Belgium', 'Zaventem', 'Europe', 1);
insert into airport(id, city, code, country, name, region, version) VALUES (2, 'New York', 'JFK', 'USA', 'New York 1', 'North-America', 1);
insert into airport(id, city, code, country, name, region, version) VALUES (3, 'New York', 'NYC', 'USA', 'New York 2', 'North-America', 1);
insert into airport(id, city, code, country, name, region, version) VALUES (4, 'Los Angeles', 'LAX', 'USA', 'Los Angeles Airport', 'North-America', 1);
insert into airport(id, city, code, country, name, region, version) VALUES (5, 'Barcelona', 'BAR', 'Spain', 'Barcelona Airport', 'Europe', 1);
insert into airport(id, city, code, country, name, region, version) VALUES (6, 'Madrid', 'MAD', 'Spain', 'Madrid Airport', 'Europe', 1);

insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(1, 'AH17', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 1, 1, 1, 2);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(2, 'AB17', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 2, 1, 2, 3);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(3, 'BB17', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 1, 1, 3 ,1);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(4, 'DD88', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 1, 1, 1 ,4);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(5, 'CX55', '2017-09-18 18:12:30', '2017-09-19 00:23:45', 2, 1, 1 ,4);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(6, 'HF58', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 2, 1, 4 ,5);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(7, 'PO00', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 2, 1, 4 ,6);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(8, 'DE89', '2017-09-18 20:12:30', '2017-09-19 02:23:45', 1, 1, 4 ,6);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(9, 'PO87', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 1, 1, 5 ,1);
insert into flight(id, name, departureTime, arrivalTime, company_id, version, airportFrom_id, airportTo_id) values(10, 'DQ44', '2017-09-18 12:12:30', '2017-09-18 18:23:45', 1, 1, 6 ,1);

insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (1, 1, 15);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (1, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (1, 0, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (2, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (2, 1, 3);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (2, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (3, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (3, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (3, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (4, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (4, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (4, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (5, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (5, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (5, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (6, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (6, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (6, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (7, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (7, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (7, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (8, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (8, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (8, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (9, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (9, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (9, 2, 20);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (10, 0, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (10, 1, 30);
insert into availableseatsperbudgetclass(Flight_id, budgetClass, available) values (10, 2, 20);

insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (1, 213.24, 5, 33.67, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (1, 23.24, 5, 30.67, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (1, 213.2, 5, 3.67, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (2, 13.24, 5, 353.67, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (2, 21.24, 5, 23.67, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (2, 213.24, 5, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (3, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (3, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (3, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (4, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (4, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (4, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (5, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (5, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (5, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (6, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (6, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (6, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (7, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (7, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (7, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (8, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (8, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (8, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (9, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (9, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (9, 113.24, 5, 0, 2);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (10, 2130.24, 7, 0, 0);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (10, 2143.24, 10, 0, 1);
insert into pricePerBudgetClass(Flight_id, base, profitPercentage, fixBonus, budgetClass) values (10, 113.24, 5, 0, 2);

insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (1, 'PAID', '1912-12-12 12:12:12', NULL, 12.21, 541.20, 'ENDORSEMENT', 1);
insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (2, 'RESERVED', '1912-12-12 12:12:12', 76.95, NULL, 754.21, 'CREDIT_CARD', 2);
insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (3, 'PAID', '1912-12-12 12:12:12', NULL, NULL, 1125.20, 'ENDORSEMENT', 3);
insert into booking(id, bookingStatus, createdOn, discountCC, discountVolume, finalPrice, paymentChoice, user_id) values (4, 'PAYMENT_PENDING', '1912-12-12 12:12:12', 76.95, 88.20, 1754.21, 'CREDIT_CARD', 1);

insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (2, 20, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (2, 10, 5);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (2, 5, 3);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (3, 20, 20);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (3, 10, 8);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (1, 17.2, 6);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (4, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (4, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (4, 20, 15);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (5, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (5, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (5, 20, 15);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (6, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (6, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (6, 20, 15);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (7, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (7, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (7, 20, 15);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (8, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (8, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (8, 20, 15);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (9, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (9, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (9, 20, 15);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (10, 5, 4);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (10, 10, 10);
insert into discountpervolume(Flight_id, volumeDiscounts, minPeople) values (10, 20, 15);

insert into ticket(Id, budgetClass, firstName, lastName, ticketPrice, version, booking_id, flight_id) VALUES (1, 'ECONOMY', 'Joris', 'Boschmans', 125.20, 1, 1, 1);
insert into ticket(Id, budgetClass, firstName, lastName, ticketPrice, version, booking_id, flight_id) VALUES (2, 'FIRST_CLASS', 'John', 'De Smedt', 1200, 1, 2, 2);
insert into ticket(Id, budgetClass, firstName, lastName, ticketPrice, version, booking_id, flight_id) VALUES (3, 'BUSINESS', 'Teofiel', 'Tester', 125.20, 1, 3, 3);




























