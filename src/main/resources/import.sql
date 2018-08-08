INSERT INTO role (id, authority) values (1, 'NORMAL');
INSERT INTO role (id, authority) values (2, 'ADMIN');

INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (1, 'serverwizard@naver.com', '$2a$10$SXZlqMGt6MukUx1stVLmPejHj.Eg6Ku18V3MSIDQ.Og6tGPhxoesW', '홍종완', '010-4681-5431', 1);
