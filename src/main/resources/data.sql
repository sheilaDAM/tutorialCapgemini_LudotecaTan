INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES ('Isabella');
INSERT INTO client(name) VALUES ('Luci');
INSERT INTO client(name) VALUES ('Gustavo');
INSERT INTO client(name) VALUES ('Carla');
INSERT INTO client(name) VALUES ('Eve');
INSERT INTO client(name) VALUES ('Lola');
INSERT INTO client(name) VALUES ('Manuela');

INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (1, 1, '2024-07-15', '2024-07-19');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (1, 2, '2024-07-20', '2024-07-24');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (2, 3, '2024-07-15', '2024-07-17');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (2, 4, '2024-07-18', '2024-07-21');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (3, 5, '2024-07-15', '2024-07-19');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (3, 6, '2024-07-20', '2024-07-23');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (3, 2, '2024-07-25', '2024-07-27');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (4, 1, '2024-07-15', '2024-07-16');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (4, 3, '2024-07-17', '2024-07-20');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (5, 4, '2024-07-21', '2024-07-24');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (6, 5, '2024-07-15', '2024-07-18');
INSERT INTO loan (client_id, game_id, start_loan_date, end_loan_date) VALUES (7, 6, '2024-07-19', '2024-07-22');



