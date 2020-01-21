-- to disable running this script every startup remove 
-- spring.datasource.initialization-mode=always from application.properties

delete from user_role;
delete from user;
delete from role;

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