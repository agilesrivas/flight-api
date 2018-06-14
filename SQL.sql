DROP DATABASE IF EXISTS db_tp5_flights;
CREATE DATABASE IF NOT EXISTS db_tp5_flights CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE db_tp5_flights;

CREATE TABLE IF NOT EXISTS `countries` (
	`id` INT AUTO_INCREMENT NOT NULL ,
    `name_Country` VARCHAR(50) NOT NULL,
    `iso` VARCHAR(5) NOT NULL,
    CONSTRAINT `pk_Country` PRIMARY KEY(`id`),
    CONSTRAINT `unq_name_Country` UNIQUE(`name_Country`),
    CONSTRAINT `unq_iso_Country` UNIQUE(`iso`)
) ENGINE = InnoDB;
ALTER TABLE countries COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `states` (
	`id` INT AUTO_INCREMENT NOT NULL ,
    `iata` VARCHAR(7) NOT NULL,
    `name_State` VARCHAR(50) NOT NULL,
    `id_Country` INT NOT NULL,
    CONSTRAINT `pk_State` PRIMARY KEY(`id`),
    CONSTRAINT `unq_iata_State` UNIQUE(`iata`),
    CONSTRAINT `fk_country_State` FOREIGN KEY(`id_Country`) REFERENCES `countries`(`id`) ON UPDATE CASCADE
) ENGINE = InnoDB;
ALTER TABLE states COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `cities` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `iata` VARCHAR(15) NOT NULL,
    `name_City` VARCHAR(50) NOT NULL,
    `id_State` INT NOT NULL,
    CONSTRAINT `pk_City` PRIMARY KEY(`id`),
    CONSTRAINT `unq_iata_City` UNIQUE(`iata`),
    CONSTRAINT `fk_state_City` FOREIGN KEY(`id_State`) REFERENCES `states`(`id`) ON UPDATE CASCADE
) ENGINE = InnoDB;
ALTER TABLE cities COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `airports` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `iata` VARCHAR(5) NOT NULL,
	`name_Airport` VARCHAR(200) NOT NULL,
	`id_City` INT NOT NULL,
    `latitude` FLOAT NOT NULL,
    `longitude` FLOAT NOT NULL,
	CONSTRAINT `id_Airport` PRIMARY KEY(`id`),
    CONSTRAINT `unq_iata_Airport` UNIQUE(`iata`),
	CONSTRAINT `unq_name_Airport` UNIQUE(`name_Airport`),
	CONSTRAINT `fk_City_Airport` FOREIGN KEY(`id_City`) REFERENCES `cities`(`id`) ON UPDATE CASCADE
) ENGINE = InnoDB;
ALTER TABLE airports COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `routes` (
	`id` INT AUTO_INCREMENT NOT NULL,
	`id_Airport_Begin` INT NOT NULL,
	`id_Airport_End` INT NOT NULL,
	`distance` INT UNSIGNED NOT NULL,
	`estimated_time` INT NOT NULL, 
	CONSTRAINT `pk_Routes` PRIMARY KEY(`id`),
	CONSTRAINT `fk_AirportBegin_Routes` FOREIGN KEY(`id_Airport_Begin`) REFERENCES `airports`(`id`) ON UPDATE CASCADE,
	CONSTRAINT `fk_AirportEnd_Routes` FOREIGN KEY(`id_Airport_End`) REFERENCES `airports`(`id`) ON UPDATE CASCADE,
    CONSTRAINT `unq_Route` UNIQUE(`id_Airport_Begin`, `id_Airport_End`)
) ENGINE = InnoDB;
ALTER TABLE routes COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `flights` (
	`id` INT AUTO_INCREMENT NOT NULL,
	`id_Route` INT NOT NULL,
    `date_Flight` VARCHAR(15),
	CONSTRAINT `pk_Flight` PRIMARY KEY (`id`),
	CONSTRAINT `fk_IdRoute_Flight` FOREIGN KEY(`id_Route`) REFERENCES `routes`(`id`) ON UPDATE CASCADE
) ENGINE = InnoDB;
ALTER TABLE flights COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `cabins` (
	`id` INT AUTO_INCREMENT NOT NULL,
	`type_Cabin` VARCHAR(50) NOT NULL,
	CONSTRAINT `pk_Cabin` PRIMARY KEY(`id`),
	CONSTRAINT `unq_Cabin` UNIQUE(`type_Cabin`)
) ENGINE = InnoDB;
ALTER TABLE cabins COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `prices` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `id_Cabin` INT NOT NULL,
    `from_Date` VARCHAR(15) NOT NULL,
    `to_Date` VARCHAR(15),
    `price` FLOAT NOT NULL,
    `state_bool` BOOLEAN,
    CONSTRAINT `pk_Price` PRIMARY KEY(`id`),
    CONSTRAINT `fk_id_Cabin_Price` FOREIGN KEY(`id_Cabin`) REFERENCES `cabins`(`id`) ON UPDATE CASCADE
) ENGINE = InnoDB;
ALTER TABLE prices COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `users` (
	`id` INT AUTO_INCREMENT NOT NULL,
	`name` VARCHAR(50) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	CONSTRAINT `pk_User` PRIMARY KEY(`id`),
	CONSTRAINT `unq_User` UNIQUE(`name`)
) ENGINE = InnoDB;
ALTER TABLE users COLLATE utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `tickets` (
	`id` INT AUTO_INCREMENT NOT NULL,
    `id_Flight` INT NOT NULL,
    `id_Price` INT NOT NULL,
	`id_User` INT NOT NULL,
    `total_price` FLOAT UNSIGNED,
    `date_flight` DATE,
    CONSTRAINT `pk_Ticket` PRIMARY KEY(id),
    CONSTRAINT `fk_id_Flight_Ticket` FOREIGN KEY(`id_Flight`) REFERENCES `flights`(`id`) ON UPDATE NO ACTION,
    CONSTRAINT `fk_id_User_Ticket` FOREIGN KEY(`id_User`) REFERENCES `users`(`id`) ON UPDATE NO ACTION,
    CONSTRAINT `fk_id_Price_Ticket` FOREIGN KEY(`id_Price`) REFERENCES `prices`(`id`) ON UPDATE NO ACTION
) ENGINE = InnoDB;
ALTER TABLE tickets COLLATE utf8_unicode_ci;

DELIMITER $$
CREATE TRIGGER `tInsertNewPrice` BEFORE INSERT
ON prices
FOR EACH ROW
BEGIN
	IF EXISTS(	SELECT * 
				FROM prices p 
				WHERE STR_TO_DATE(NEW.from_Date, '%d/%m/%Y') BETWEEN STR_TO_DATE(p.from_Date, '%d/%m/%Y') AND STR_TO_DATE(p.to_Date, '%d/%m/%Y') 
				OR STR_TO_DATE(NEW.to_Date, '%d/%m/%Y') BETWEEN STR_TO_DATE(p.from_Date, '%d/%m/%Y') AND STR_TO_DATE(p.to_Date, '%d/%m/%Y')
				OR STR_TO_DATE(p.from_Date, '%d/%m/%Y') BETWEEN STR_TO_DATE(NEW.from_Date, '%d/%m/%Y') AND STR_TO_DATE(NEW.to_Date, '%d/%m/%Y')
				OR STR_TO_DATE(p.to_Date, '%d/%m/%Y') BETWEEN STR_TO_DATE(NEW.from_Date, '%d/%m/%Y') AND STR_TO_DATE(NEW.to_Date, '%d/%m/%Y')
				AND p.id_Cabin = NEW.id_Cabin) THEN
        SIGNAL SQLSTATE '25440' SET MESSAGE_TEXT = 'Dates collides with others dates - Prices';
	END IF;
END;
$$
