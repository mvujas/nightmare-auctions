-- to disable running this script every startup remove 
-- spring.datasource.initialization-mode=always from application.properties

delete from user_role;
delete from user;
delete from role;
delete from item;
delete from category;

INSERT INTO role(id, name)
VALUES (1, 'USER'), 
       (2, 'ADMIN');
       
INSERT INTO user(id, username, email, password)
VALUES (1, 'mvujas', 'pera@example.com', '$2a$10$DGLg18J2x5HELp88hQrVVua7a5svZzBGD44IFjD76YJBDu/cT/TCq'), -- password: maliPerica
       (2, 'admin', 'admin@example.com', '$2a$10$RaAr5cYGq6.NSc2nODLgYeDsgWfalcJuVJyQg.nQPk.cRPJkPE4cq'); -- password: admin123
       
INSERT INTO user_role(users_id, roles_id)
VALUES (2, 1), -- USER to admin
       (2, 2), -- ADMIN to admin
       (1, 1); -- USER to mvujas
       
INSERT INTO category(id, name)
VALUES (1, 'CARS'),
       (2, 'TECH');
       
INSERT INTO item(id, name, starting_price, category_id)
VALUES (1, 'Audi Q5 2.0 tdi 190 Quatt At 2015. godište', 18900, 1), -- CATEGORY: CARS
       (2, 'Peugeot 2008 // Feline Titane // 2013. godište', 8200, 1), -- CATEGORY: CARS
       (3, 'Renault Captur INTENS TCE 100 2019. godište', 18800, 1); -- CATEGORY: CARS