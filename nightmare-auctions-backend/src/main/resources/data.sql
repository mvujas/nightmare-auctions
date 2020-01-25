-- to disable running this script every startup remove 
-- spring.datasource.initialization-mode=always from application.properties

delete from grade;
delete from grade_holder;
delete from bid;
delete from item;
delete from user_role;
delete from user;
delete from role;
delete from category;

INSERT INTO role(id, name)
VALUES (1, 'USER'), 
       (2, 'ADMIN');
       
INSERT INTO user(id, username, email, password, registration_time)
VALUES (1, 'mvujas', 'pera@example.com', '$2a$10$DGLg18J2x5HELp88hQrVVua7a5svZzBGD44IFjD76YJBDu/cT/TCq', now()), -- password: maliPerica
       (2, 'admin', 'admin@example.com', '$2a$10$RaAr5cYGq6.NSc2nODLgYeDsgWfalcJuVJyQg.nQPk.cRPJkPE4cq', now()), -- password: admin123
       (3, 'stasa', 'stasa@example.com', '$2a$10$RaAr5cYGq6.NSc2nODLgYeDsgWfalcJuVJyQg.nQPk.cRPJkPE4cq', now()); -- password: admin123
       
INSERT INTO user_role(users_id, roles_id)
VALUES (2, 1), -- USER to admin
       (2, 2), -- ADMIN to admin
       (1, 1), -- USER to mvujas
       (3, 1); -- USER to stasa
       
INSERT INTO category(id, name)
VALUES (1, 'CARS'),
       (2, 'TECH');
       
INSERT INTO item(id, name, starting_price, category_id, author_id, posting_time)
VALUES (1, 'Audi Q5 2.0 tdi 190 Quatt At 2015. godište', 18900, 1, 1, '2011-05-01 12:07:26'), -- CATEGORY: CARS, AUTHOR: mvujas
       (2, 'Peugeot 2008 // Feline Titane // 2013. godište', 8200, 1, 1, '2009-04-03 02:23:49'), -- CATEGORY: CARS, AUTHOR: mvujas
       (3, 'Renault Captur INTENS TCE 100 2019. godište', 18800, 1, 2, '2007-12-12 05:24:53'), -- CATEGORY: CARS, AUTHOR: admin
       (4, 'Ford Fusion 1.6 ben NOVO 2002. godište', 2200, 1, 1, '2019-04-30 21:13:34'), -- CATEGORY: CARS, AUTHOR: mvujas
       (5, 'Fiat 500L Treking 2017. godište', 10500, 1, 1, '2018-10-08 01:03:03'), -- CATEGORY: CARS, AUTHOR: mvujas
       (6, 'Kia XCeed 1.0 T-GDi Flow 2019. godište - Novo', 20570, 1, 1, '2016-07-08 15:36:07'), -- CATEGORY: CARS, AUTHOR: mvujas
       (7, 'Peugeot 5008 1.6 HDI CH 2010. godište', 4400, 1, 1, '2002-10-11 18:47:59'), -- CATEGORY: CARS, AUTHOR: mvujas
       (8, 'Volkswagen Passat B8 HIGHLINE-BLUEMOTION 2016. godište', 13500, 1, 1, '2014-07-06 16:22:43'), -- CATEGORY: CARS, AUTHOR: mvujas
       (9, 'Citroen C4 Picasso 1.6 hdi 2008. godište', 3499, 1, 1, '2010-09-06 04:40:28'), -- CATEGORY: CARS, AUTHOR: mvujas
       (10, 'Alfa Romeo Stelvio SUPER 190 AWD AT8 2019. godište', 40990, 1, 1, '2016-04-09 07:15:26'), -- CATEGORY: CARS, AUTHOR: mvujas
       (11, 'Opel Insignia 2.0d BI TURBO CH 2012. godišt', 6890, 1, 1, '2015-10-04 06:45:54'), -- CATEGORY: CARS, AUTHOR: mvujas
       (12, 'Fiat Panda 1,3 MJT 2014. godište', 3999, 1, 1, '2006-10-28 22:32:01'), -- CATEGORY: CARS, AUTHOR: mvujas
       (13, 'Ford Fiesta 1.25 2017. godište', 8590, 1, 1, '2011-02-19 13:30:21'), -- CATEGORY: CARS, AUTHOR: mvujas
       (14, 'Peugeot 308 1.6 HDI EXECUTIVE 2014. godište', 6999, 1, 1, '2016-07-16 06:12:45'); -- CATEGORY: CARS, AUTHOR: mvujas
       
INSERT INTO bid(id, posting_time, price, author_id, item_id)
VALUES (1, '2018-09-15 06:47:24', 21000, 2, 1),
       (2, '2012-06-27 08:40:16', 21001, 2, 1),
       (3, '2012-09-19 18:01:29', 21005, 2, 1),
       (4, '2012-10-19 21:35:46', 210000, 2, 1),
       (5, '2013-11-13 11:05:03', 1000000, 2, 1),
       (6, now(), 10000, 2, 2),
       (7, now(), 12000, 2, 2),
       (8, now(), 14000, 2, 2),
       (9, now(), 20000, 2, 2),
       (10, now(), 20000, 1, 3),
       (11, now(), 21000, 3, 3),
       (12, now(), 22000, 1, 3),
       (13, now(), 100000, 3, 3),
       (14, now(), 120000, 1, 3),
       (15, now(), 121000, 3, 3),
       (16, now(), 150000, 1, 3),
       (17, now(), 200000, 3, 3);
       
UPDATE item i
SET closing_time = now()
WHERE i.id = 1 OR
      i.id = 3;
      --i.id =  OR

INSERT INTO grade_holder(id, value, giving_grade_id, receiving_grade_id)
VALUES (1, 2, 2, 1),
       (2, null, 1, 2),
       (3, null, 3, 2),
       (4, 0, 2, 3);

INSERT INTO grade(id, author_grade_id,	bid_id, buyer_grade_id)
VALUES (1, 2, 5, 1),
       (3, 4, 17, 3);
       
       
       
       
       
       
       
       