DROP DATABASE IF EXISTS db_tp5_flights;
CREATE DATABASE IF NOT EXISTS db_tp5_flights CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE db_tp5_flights;

CREATE TABLE IF NOT EXISTS Countries (
	id INT AUTO_INCREMENT NOT NULL ,
    name_Country VARCHAR(50) NOT NULL,
    iso VARCHAR(5) NOT NULL,
    CONSTRAINT pk_Country PRIMARY KEY(id),
    CONSTRAINT unq_name_Country UNIQUE(name_Country),
    CONSTRAINT unq_iso_Country UNIQUE(iso)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS States (
	id INT AUTO_INCREMENT NOT NULL ,
    iata VARCHAR(7) NOT NULL,
    name_State VARCHAR(50) NOT NULL,
    id_Country INT NOT NULL,
    CONSTRAINT pk_State PRIMARY KEY(id),
    CONSTRAINT unq_name_State UNIQUE(name_State),
    CONSTRAINT unq_iata_State UNIQUE(iata),
    CONSTRAINT fk_country_State FOREIGN KEY(id_Country) REFERENCES Countries(id) ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Cities (
	id INT AUTO_INCREMENT NOT NULL,
    iata VARCHAR(7) NOT NULL,
    name_City VARCHAR(50) NOT NULL,
    id_State INT NOT NULL,
    CONSTRAINT pk_City PRIMARY KEY(id),
    CONSTRAINT unq_iata_City UNIQUE(iata),
    CONSTRAINT unq_name_City UNIQUE(name_City),
    CONSTRAINT fk_state_City FOREIGN KEY(id_State) REFERENCES States(id) ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Airports (
	id INT AUTO_INCREMENT NOT NULL,
    iata VARCHAR(5) NOT NULL,
	name_Airport VARCHAR(50) NOT NULL,
	id_City INT NOT NULL,
    latitude FLOAT UNSIGNED NOT NULL,
    longitude FLOAT UNSIGNED NOT NULL,
	CONSTRAINT id_Airport PRIMARY KEY(id),
    CONSTRAINT unq_iata_Airport UNIQUE(iata),
	CONSTRAINT unq_name_Airport UNIQUE(name_Airport),
	CONSTRAINT fk_City_Airport FOREIGN KEY(id_City) REFERENCES Cities(id) ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Routes (
	id INT AUTO_INCREMENT NOT NULL,
	id_Airport_Begin INT NOT NULL,
	id_Airport_End INT NOT NULL,
	distance INT UNSIGNED NOT NULL,
	estimated_time INT NOT NULL, 
	CONSTRAINT pk_Routes PRIMARY KEY(id),
	CONSTRAINT fk_AirportBegin_Routes FOREIGN KEY(id_Airport_Begin) REFERENCES Airports(id) ON UPDATE CASCADE,
	CONSTRAINT fk_AirportEnd_Routes FOREIGN KEY(id_Airport_End) REFERENCES Airports(id) ON UPDATE CASCADE,
    CONSTRAINT unq_Route UNIQUE(id_Airport_Begin, id_Airport_End)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Flights (
	id INT AUTO_INCREMENT NOT NULL,
	id_Route INT NOT NULL,
    date_Flight DATE,
	CONSTRAINT pk_Flight PRIMARY KEY (id),
	CONSTRAINT fk_IdRoute_Flight FOREIGN KEY(id_Route) REFERENCES Routes(id) ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Cabins (
	id INT AUTO_INCREMENT NOT NULL,
	type_Cabin VARCHAR(50) NOT NULL,
	CONSTRAINT pk_Cabin PRIMARY KEY(id),
	CONSTRAINT unq_Cabin UNIQUE(type_Cabin)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Prices (
	id INT AUTO_INCREMENT NOT NULL,
    id_Cabin INT NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE,
    price FLOAT NOT NULL,
    state_bool BOOLEAN,
    CONSTRAINT pk_Price PRIMARY KEY(id),
    CONSTRAINT fk_id_Cabin_Price FOREIGN KEY(id_Cabin) REFERENCES Cabins(id) ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Tickets (
	id INT AUTO_INCREMENT NOT NULL,
    id_Flight INT NOT NULL,
    id_Cabin INT NOT NULL,
    total_price FLOAT UNSIGNED,
    date_flight DATE,
    CONSTRAINT pk_Ticket PRIMARY KEY(id),
    CONSTRAINT fk_id_Flight_Ticket FOREIGN KEY(id_Flight) REFERENCES Flights(id) ON UPDATE NO ACTION,
    CONSTRAINT fk_id_Cabin_Ticket FOREIGN KEY(id_Cabin) REFERENCES Cabins(id) ON UPDATE NO ACTION
) ENGINE = InnoDB;

DELIMITER $$
CREATE TRIGGER tInsertNewPrice BEFORE INSERT
ON Prices
FOR EACH ROW
BEGIN
	IF EXISTS(SELECT * FROM Prices WHERE toDate IS NULL AND id_Cabin = new.id_Cabin) THEN
        UPDATE Prices SET toDate = new.fromDate, state_bool = FALSE WHERE toDate IS NULL AND id_Cabin = new.id_Cabin;
        SET NEW.state_bool = TRUE;
	END IF;
END;
$$

DELIMITER $$
CREATE TRIGGER tInsertNewTicket AFTER INSERT
ON Tickets
FOR EACH ROW
BEGIN
	DECLARE vTotal_Price FLOAT DEFAULT 0;
    DECLARE vPrice_x_Km FLOAT DEFAULT 0;
    DECLARE vDistance INT DEFAULT 0;
    DECLARE vDate DATE;
    
	IF EXISTS(SELECT * FROM Tickets WHERE id = NEW.id) THEN
		
        SET vDate = (	
						SELECT f.date_Flight 
						FROM Flights f 
						INNER JOIN Tickets t ON t.id_Flight = f.id 
						WHERE t.id = NEW.id
					);	
        
        SET vDistance = (	
							SELECT r.distance
							FROM Flights f
							INNER JOIN Routes r ON r.id = f.id_Route
							WHERE f.id = NEW.id_Flight
						);
        
        SET vPrice_x_Km = (
							SELECT p.price
							FROM Cabin c
							INNER JOIN Prices p ON p.id_Cabin = c.id
							WHERE 	c.id = NEW.id_Cabin AND
									(vDate BETWEEN p.fromDate AND IF(p.toDate IS NULL, ADDDATE(p.fromDate, INTERVAL 1000 YEAR), p.toDate))
						);
                        
		SET vTotal_Price = vPrice_x_Km * vDistance;
        UPDATE Tickets SET total_price = vTotal_Price, date_flight = vDate WHERE id = NEW.id;
	
    ELSE
		SIGNAL SQLSTATE '15120' SET MESSAGE_TEXT = 'Ticket not found';
    END IF;
    
END;
$$