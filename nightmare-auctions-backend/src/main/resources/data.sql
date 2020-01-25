-- to disable running this script every startup remove 
-- spring.datasource.initialization-mode=always from application.properties

delete from grade;
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
       (2, 'admin', 'admin@example.com', '$2a$10$RaAr5cYGq6.NSc2nODLgYeDsgWfalcJuVJyQg.nQPk.cRPJkPE4cq', now()); -- password: admin123
       
INSERT INTO user_role(users_id, roles_id)
VALUES (2, 1), -- USER to admin
       (2, 2), -- ADMIN to admin
       (1, 1); -- USER to mvujas
       
INSERT INTO category(id, name)
VALUES (1, 'CARS'),
       (2, 'TECH');
       
INSERT INTO item(id, name, starting_price, category_id, author_id, posting_time)
VALUES (1, 'Audi Q5 2.0 tdi 190 Quatt At 2015. godište', 18900, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (2, 'Peugeot 2008 // Feline Titane // 2013. godište', 8200, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (3, 'Renault Captur INTENS TCE 100 2019. godište', 18800, 1, 2, now()), -- CATEGORY: CARS, AUTHOR: admin
       (4, 'Ford Fusion 1.6 ben NOVO 2002. godište', 2200, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (5, 'Fiat 500L Treking 2017. godište', 10500, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (6, 'Kia XCeed 1.0 T-GDi Flow 2019. godište - Novo', 20570, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (7, 'Peugeot 5008 1.6 HDI CH 2010. godište', 4400, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (8, 'Volkswagen Passat B8 HIGHLINE-BLUEMOTION 2016. godište', 13500, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (9, 'Citroen C4 Picasso 1.6 hdi 2008. godište', 3499, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (10, 'Alfa Romeo Stelvio SUPER 190 AWD AT8 2019. godište', 40990, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (11, 'Opel Insignia 2.0d BI TURBO CH 2012. godišt', 6890, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (12, 'Fiat Panda 1,3 MJT 2014. godište', 3999, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (13, 'Ford Fiesta 1.25 2017. godište', 8590, 1, 1, now()), -- CATEGORY: CARS, AUTHOR: mvujas
       (14, 'Peugeot 308 1.6 HDI EXECUTIVE 2014. godište', 6999, 1, 1, now()); -- CATEGORY: CARS, AUTHOR: mvujas