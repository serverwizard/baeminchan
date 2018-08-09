INSERT INTO role (id, authority) values (1, 'NORMAL');
INSERT INTO role (id, authority) values (2, 'ADMIN');

INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (1, 'serverwizard@naver.com', '$2a$10$SXZlqMGt6MukUx1stVLmPejHj.Eg6Ku18V3MSIDQ.Og6tGPhxoesW', '홍종완', '010-4333-5431', 1);
INSERT INTO user (id, user_id, password, name, phone_number, role_id) values (2, 'javajigi@slipp.com', '$2a$10$SXZlqMGt6MukUx1stVLmPejHj.Eg6Ku18V3MSIDQ.Og6tGPhxoesW', '박재성', '010-4681-5111', 2);

INSERT INTO category (id, parent_category_id, name) values (1, null, '밑반찬');


INSERT INTO side_dish (name, description, price, sale_price, weight) values ('장조림','이것은 설명입니다', 1000, 500, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('김치','이것은 설명입니다', 5000, 100, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('볶음밥','이것은 설명입니다', 1000, 1000, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('포테토칩','이것은 설명입니다', 100, 50, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('김포카리','이것은 설명입니다', 1000, 800, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('콜김라콜김라콜김라콜','이것은 설명입니다', 200, 100, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('사이김다','이것은 설명입니다', 5000, 3500, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('스프라이트','이것은 설명입니다', 1000, 700, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('사과쥬스','이것은 설명입니다', 1000, 700, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('탕수육','이것은 설명입니다', 1000, 800, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('치킨','이것은 설명입니다', 1000, 900, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('피자','이것은 설명입니다', 5000, 4000, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('햄버거','이것은 설명입니다', 1000, 800, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('허니콤보','이것은 설명입니다', 1000, 500, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('초콜렛','이것은 설명입니다', 2000, 1000, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('아이스크림','이것은 설명입니다', 1000, 100, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('젤리','이것은 설명입니다', 1000, 10, 100);
INSERT INTO side_dish (name, description, price, sale_price, weight) values ('계피','이것은 설명입니다', 5000, 1, 100);