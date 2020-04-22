INSERT INTO `authorities` (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `authorities` (id, name) VALUES (2, 'ROLE_BUYER');
INSERT INTO `authorities` (id, name) VALUES (3, 'ROLE_SELLER');

INSERT INTO `users` (id, email, enabled, first_name, last_name, last_password_reset_date, password, username) VALUES (1,'admin@webshop.com', true, 'John', 'Doe','2017-10-01 21:58:58', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin');

INSERT INTO `user_authority` (user_id, authority_id) VALUES (1, 1);
