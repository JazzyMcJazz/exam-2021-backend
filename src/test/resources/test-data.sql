DELETE FROM candidate;
DELETE FROM party;

INSERT INTO party (id, name) VALUES ('A', 'Socialdemokratiet');
INSERT INTO party (id, name) VALUES ('O', 'Dansk Folkeparti');

INSERT INTO candidate (id, name, party_id, votes) VALUES (101, 'Marcel Meijer', 'A', 0);
INSERT INTO candidate (id, name, party_id, votes) VALUES (102, 'Michael Kristensen', 'A', 0);
INSERT INTO candidate (id, name, party_id, votes) VALUES (103, 'Helle Hansen', 'A', 0);

INSERT INTO candidate (id, name, party_id, votes) VALUES (104, 'Per Mortensen', 'O', 0);