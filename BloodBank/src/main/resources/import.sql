insert into adresses (id, city, country, number, street) values (2000, 'Novi Sad', 'Serbia', '15', 'Puskinova');
insert into adresses (id, city, country, number, street) values (2001, 'Novi Sad', 'Serbia', '21', 'Turgenjeva');
insert into adresses (id, city, country, number, street) values (2002, 'Nis', 'Serbia', '34', 'Tolstojeva');
insert into adresses (id, city, country, number, street) values (2003, 'Novi Sad', 'Serbia', '54', 'Gogoljeva');
insert into adresses (id, city, country, number, street) values (2004, 'Kragujevac', 'Serbia', '21', 'Desanke Maksimovic');
insert into adresses (id, city, country, number, street) values (2005, 'Belgrade', 'Serbia', '24', 'Cara Dusana');

insert into blood_bank_centers (id, average_grade, description, name, address_id) values (2006, 4.5, 'Bas dobra banka vjeruj mi', 'Dobra banka', 2000);
insert into blood_bank_centers (id, average_grade, description, name, address_id) values (2007, 3.6, 'Ekstra dobra banka brate vjeruj mi', 'Ekstra dobra banka', 2004);
insert into blood_bank_centers (id, average_grade, description, name, address_id) values (2008, 2.1, 'Najbolja banka krvi u Beogradu', 'Bloody Marry', 2005);

insert into users(id, dob, email, first_name, gender, last_name, password, personal_id, phone_number, role, address_id) values (2009, '1999-05-29', 'stefan@gmail.com', 'Stefan', 'male', 'Tosic', 'stefke', 1593281593284, '0635684284', 2, 2001);
insert into users(id, dob, email, first_name, gender, last_name, password, personal_id, phone_number, role, address_id) values (2010, '1997-05-29', 'vanja@gmail.com', 'Vanja', 'female', 'Teodorovic', 'vanjilica', 1693214533184, '0665827284', 2, 2002);
insert into users(id, dob, email, first_name, gender, last_name, password, personal_id, phone_number, role, address_id) values (2011, '1998-04-04', 'jelena@gmail.com', 'Jelena', 'female', 'Dinic', 'jelena', 2093989533184, '0666327284', 0, 2003);
insert into users(id, dob, email, first_name, gender, last_name, password, personal_id, phone_number, role, address_id) values (2012,  '1995-05-03', 'dusko@gmail.com', 'Dusko', 'male', 'Radicic', 'dusko', 8653989533542, '0666687484', 1, 2004);

insert into registered_users (blood_type, profession, profession_info, id, penalties) values (3, 'Engineer', 'dunno', 2009, 0);
insert into registered_users (blood_type, profession, profession_info, id, penalties) values (6, 'Engineer', 'nzm', 2010, 0);


insert into staff (id, blood_bank_center_id) values (2011, 2006);

insert into system_administrators (id) values (2012);

insert into appointments (id, user_id, date_time, duration, status) values (2001, 2010, '1995-05-03 12:00:00', 30, 0)
insert into appointments (id, user_id, date_time, duration, status) values (2002, 2010, '1995-05-03 12:15:00', 30, 0)

insert into equipment (id, type, blood_bank_center_id, quantity) values (1, 0, 2006, 100)
insert into equipment (id, type, blood_bank_center_id, quantity) values (2, 1, 2006, 100)
insert into equipment (id, type, blood_bank_center_id, quantity) values (3, 2, 2006, 100)
insert into equipment (id, type, blood_bank_center_id, quantity) values (4, 3, 2006, 100)
insert into equipment (id, type, blood_bank_center_id, quantity) values (5, 4, 2006, 100)
insert into equipment (id, type, blood_bank_center_id, quantity) values (6, 5, 2006, 100)

insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (1, 2006, 50, 0)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (2, 2006, 50, 1)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (3, 2006, 50, 2)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (4, 2006, 50, 3)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (5, 2006, 50, 4)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (6, 2006, 50, 5)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (7, 2006, 50, 6)
insert into blood_supplies (id, blood_bank_center_id, amount, blood_type) values (8, 2006, 50, 7)