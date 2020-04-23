INSERT INTO `authorities` (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `authorities` (id, name) VALUES (2, 'ROLE_BUYER');
INSERT INTO `authorities` (id, name) VALUES (3, 'ROLE_SELLER');

INSERT INTO `users` (user_type, id, email, enabled, last_password_reset_date, name, password, username, category, total_money_spent) VALUES (0, 1,'admin@webshop.com', true, '2017-10-01 21:58:58', 'Admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'admin', null, null);
INSERT INTO `users` (user_type, id, email, enabled, last_password_reset_date, name, password, username, category, total_money_spent) VALUES (1, 2,'john@doe.com', true, '2017-10-01 21:58:58', 'John Doe', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'john.doe', null, null);

INSERT INTO `user_authority` (user_id, authority_id) VALUES (1, 1);
INSERT INTO `user_authority` (user_id, authority_id) VALUES (2, 3);

INSERT INTO `stores` (id, frequent_buyer_discount, name, owner_id) VALUES (1, 0.1, 'Johns Store', 2);

INSERT INTO `product_categories` (id, name) VALUES (1, 'Category 1');
INSERT INTO `product_categories` (id, name) VALUES (2, 'Category 2');
