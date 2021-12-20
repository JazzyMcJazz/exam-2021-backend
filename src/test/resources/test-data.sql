DELETE FROM candidate;
DELETE FROM party;

INSERT INTO party (id, name) VALUES ('A', 'Socialdemokratiet');
INSERT INTO party (id, name) VALUES ('O', 'Dansk Folkeparti');

INSERT INTO candidate (id, name, party_id) VALUES (101, 'Marcel Meijer', 'A');
INSERT INTO candidate (id, name, party_id) VALUES (102, 'Michael Kristensen', 'A');
INSERT INTO candidate (id, name, party_id) VALUES (103, 'Helle Hansen', 'A');

INSERT INTO candidate (id, name, party_id) VALUES (104, 'Per Mortensen', 'O');