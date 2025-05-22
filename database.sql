-- Crear base de datos y tablas para java BOARD GAMES
CREATE DATABASE IF NOT EXISTS board_games_db;
USE board_games_db;

DROP TABLE IF EXISTS GAME_CATEGORY;
DROP TABLE IF EXISTS RESERVATIONS;
DROP TABLE IF EXISTS DOMINIOS;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS CATEGORIES;
DROP TABLE IF EXISTS GAMES; 


    CREATE TABLE USERS (
        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
        name VARCHAR(50) NOT NULL,
        mail VARCHAR(50) UNIQUE NOT NULL,
        mail_verified BOOLEAN DEFAULT FALSE,
        password VARCHAR(100) NOT NULL,
        reg_date DATE NOT NULL DEFAULT (CURDATE())
    );

    CREATE TABLE CATEGORIES (
        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
        name VARCHAR(100) NOT NULL
    );
    CREATE TABLE GAMES (
        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
        name VARCHAR(50) NOT NULL,
        min_players INT NOT NULL,
        max_players INT NOT NULL,
        duration TIME NOT NULL,
        min_age INT NOT NULL,
        src_img VARCHAR(200)
    );

    CREATE TABLE GAME_CATEGORY (
        id_game INT NOT NULL,
        id_category INT NOT NULL,
        FOREIGN KEY (id_game) REFERENCES GAMES(id),
        FOREIGN KEY (id_category) REFERENCES CATEGORIES(id),
        PRIMARY KEY (id_game, id_category)
    );


    CREATE TABLE RESERVATIONS (
        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
        id_user INT NOT NULL,
        id_game INT NOT NULL,
        num_players INT NOT NULL,
        reservation_date DATE NOT NULL,
        time_start TIME NOT NULL,
        time_end TIME NOT NULL,
        FOREIGN KEY (id_user) REFERENCES USERS(id),
        FOREIGN KEY (id_game) REFERENCES GAMES(id)
    );


-- Crear tabla dominios
CREATE TABLE dominios (
    domain_type VARCHAR(50),
    tld VARCHAR(20),
    manager VARCHAR(255)
);

-- Insertar dominios
INSERT INTO dominios (domain_type, tld, manager) VALUES ('generic', '.com', NULL);
INSERT INTO dominios (domain_type, tld, manager) VALUES ('generic', '.es', NULL);

-- Crear un usuario para la conexion de java
CREATE USER IF NOT EXISTS  'JavaDev'@'localhost' IDENTIFIED BY 'Java12345';
GRANT ALL PRIVILEGES ON board_games_db.* TO 'JavaDev'@'localhost';
FLUSH PRIVILEGES;


-- Trigger para UPDATE
DELIMITER //
CREATE TRIGGER check_email_before_insert
BEFORE INSERT ON USERS
FOR EACH ROW
BEGIN
    DECLARE var_dominio VARCHAR(15);
    SET NEW.mail = LOWER(NEW.mail);

    IF NEW.mail LIKE '%@%.%' THEN
        SET var_dominio = CONCAT('.', SUBSTRING_INDEX(NEW.mail, '.', -1));

        IF (SELECT COUNT(*) FROM dominios WHERE tld = var_dominio) > 0 THEN
            SET NEW.mail_verified = TRUE;
        END IF;
    END IF;
END;//

CREATE TRIGGER check_email_before_update
BEFORE UPDATE ON USERS
FOR EACH ROW
BEGIN
    DECLARE var_dominio VARCHAR(15);
    SET NEW.mail = LOWER(NEW.mail);

    IF NEW.mail LIKE '%@%.%' THEN
        SET var_dominio = CONCAT('.', SUBSTRING_INDEX(NEW.mail, '.', -1));

        IF (SELECT COUNT(*) FROM dominios WHERE tld = var_dominio) > 0 THEN
            SET NEW.mail_verified = TRUE;
        END IF;
    END IF;
END;//
DELIMITER ;

-- Insertar datos de usuario
INSERT INTO USERS (name, mail, password) VALUES
('admin','admin','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

-- Inserts de los juegos de mesa 
INSERT INTO CATEGORIES (name) VALUES
('Cartas'),
('Deducción'),
('Estrategia'),
('Infantil'),
('Narrativo'),
('Party'),
('Rol');
INSERT INTO GAMES (name, min_players, max_players, duration, min_age, src_img) VALUES
('Arnak', 1, 4, '01:30:00', 12, 'img/arnak.png'),
('Catan', 3, 4, '01:15:00', 10, 'img/catan.png'),
('Caza Bombas', 2, 4, '00:20:00', 8, 'img/caza-bombas.png'),
('Código Secreto', 2, 8, '00:15:00', 10, 'img/codigo-secreto.png'),
('Cthulhu', 2, 5, '01:00:00', 14, 'img/cthulhu.png'),
('Dixit', 3, 6, '00:30:00', 8, 'img/dixit.png'),
('Happy Mochi', 2, 4, '00:20:00', 4, 'img/happy-mochi.png'),
('Hitster', 2, 10, '00:30:00', 16, 'img/hitster.png'),
('Mansiones de la Locura', 1, 5, '02:00:00', 14, 'img/mansiones-locura.png'),
('Misión Cumplida', 1, 4, '00:20:00', 8, 'img/mision-cumplida.png'),
('Pelusas', 2, 5, '00:30:00', 6, 'img/pelusas.png'),
('Root', 2, 4, '01:30:00', 10, 'img/root.png'),
('Sail', 2, 2, '00:30:00', 8, 'img/sail.png'),
('Sherlock', 1, 8, '00:30:00', 10, 'img/sherlock.png'),
('Survive Island', 2, 4, '00:45:00', 8, 'img/survive-island.png'),
('Sushi Go', 2, 5, '00:20:00', 8, 'img/sushi-go.png'),
('Uno', 2, 10, '00:30:00', 7, 'img/uno.png'),
('Wingspan', 1, 5, '01:00:00', 10, 'img/wingspan.png');

INSERT INTO GAME_CATEGORY (id_game, id_category) VALUES
(1, 3),   -- Arnak - Estrategia
(2, 3),   -- Catan - Estrategia
(3, 6),   -- Caza Bombas - Party
(4, 6),   -- Código Secreto - Party
(4, 2),   -- Código Secreto - Deducción
(5, 7),   -- Cthulhu - Rol
(5, 5),   -- Cthulhu - Narrativo
(6, 1),   -- Dixit - Cartas
(6, 5),   -- Dixit - Narrativo
(7, 4),   -- Happy Mochi - Infantil
(8, 6),   -- Hitster - Party
(9, 7),   -- Mansiones de la Locura - Rol
(9, 5),   -- Mansiones de la Locura - Narrativo
(10, 1),  -- Misión Cumplida - Cartas
(10, 2),  -- Misión Cumplida - Deducción
(11, 4),  -- Pelusas - Infantil
(11, 1),  -- Pelusas - Cartas
(12, 3),  -- Root - Estrategia
(13, 3),  -- Sail - Estrategia
(14, 1),  -- Sherlock - Cartas
(14, 2),  -- Sherlock - Deducción
(15, 3),  -- Survive Island - Estrategia
(15, 4),  -- Survive Island - Infantil
(16, 6),  -- Sushi Go - Party
(16, 1),  -- Sushi Go - Cartas
(17, 6),  -- Uno - Party
(17, 1),  -- Uno - Cartas
(17, 4),  -- Uno - Infantil 
(18, 3);  -- Wingspan - Estrategia

SELECT "Script ejecutado correctamente" AS resultado;