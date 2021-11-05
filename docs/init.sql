CREATE sequence hibernate_sequence start 1 increment 1;
CREATE TABLE currency (id int8 not null, buying_rate float8 not null, currency_from varchar(255) not null, currency_to varchar(255) not null, selling_rate float8 not null, primary key (id));

INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('1', 'USD', 'EUR', '0.87', '0.86');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('2', 'USD', 'CHF', '0.92', '0.91');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('3', 'USD', 'GBP', '0.75', '0.74');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('4', 'USD', 'HUF', '312', '311');

INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('5', 'EUR', 'USD', '1.16', '1.15');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('6', 'EUR', 'CHF', '1.06', '1.05');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('7', 'EUR', 'GBP', '0.86', '0.85');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('8', 'EUR', 'HUF', '361', '360');

INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('9', 'CHF', 'EUR', '0.95', '0.94');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('10', 'CHF', 'USD', '1.2', '1.1');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('11', 'CHF', 'GBP', '0.82', '0.81');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('12', 'CHF', 'HUF', '342', '341');

INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('13', 'GBP', 'EUR', '1.17', '1.16');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('14', 'GBP', 'USD', '1.35', '1.34');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('15', 'GBP', 'CHF', '1.24', '1.22');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('16', 'GBP', 'HUF', '420', '419');

INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('17', 'HUF', 'EUR', '0.0028', '0.0027');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('18', 'HUF', 'USD', '0.0033', '0.0032');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('19', 'HUF', 'CHF', '0.003', '0.0029');
INSERT INTO currency (id, currency_from, currency_to, buying_rate, selling_rate) VALUES ('20', 'HUF', 'GBP', '0.0024', '0.0023');