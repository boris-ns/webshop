INSERT INTO `authorities` (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `authorities` (id, name) VALUES (2, 'ROLE_BUYER');
INSERT INTO `authorities` (id, name) VALUES (3, 'ROLE_SELLER');

INSERT INTO `users` (dtype, id, email, enabled, last_password_reset_date, name, password, username, category, total_money_spent, store_id) VALUES ('user', 1,'admin@webshop.com', true, '2017-10-01 21:58:58', 'Admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin', null, null, null);

INSERT INTO `user_authority` (user_id, authority_id) VALUES (1, 1);
