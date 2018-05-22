CREATE DATABASE IF NOT EXISTS db_tp5_flights;
USE dbdb_tp5_flights;

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
    iata VARCHAR(5) NOT NULL,
    name_State VARCHAR(50) NOT NULL,
    id_Country INT NOT NULL,
    CONSTRAINT pk_State PRIMARY KEY(id),
    CONSTRAINT unq_name_State UNIQUE(name_State),
    CONSTRAINT unq_iata_State UNIQUE(iata),
    CONSTRAINT fk_country_State FOREIGN KEY(id_Country) REFERENCES Countries(id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Cities (
	id INT AUTO_INCREMENT NOT NULL,
    iata VARCHAR(5) NOT NULL,
    name_City VARCHAR(50) NOT NULL,
    id_State INT NOT NULL,
    CONSTRAINT pk_City PRIMARY KEY(id),
    CONSTRAINT unq_iata_City UNIQUE(iata),
    CONSTRAINT unq_name_City UNIQUE(name_City),
    CONSTRAINT fk_state_City FOREIGN KEY(id_State) REFERENCES States(id)
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
	CONSTRAINT fk_City_Airport FOREIGN KEY(id_City) REFERENCES Cities(id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Routes (
	id INT AUTO_INCREMENT NOT NULL,
	id_Airport_Begin INT NOT NULL,
	id_Airport_End INT NOT NULL,
	distance FLOAT UNSIGNED NOT NULL,
	estimated_times INT NOT NULL, 
	CONSTRAINT pk_Routes PRIMARY KEY(id),
	CONSTRAINT fk_AirportBegin_Routes FOREIGN KEY(id_Airport_Begin) REFERENCES Airports(id),
	CONSTRAINT fk_AirportEnd_Routes FOREIGN KEY(id_Airport_End) REFERENCES Airports(id),
    CONSTRAINT unq_Route UNIQUE(id_Airport_Begin, id_Airport_End)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Cabins (
	id INT AUTO_INCREMENT NOT NULL,
	type_Cabin VARCHAR(50) NOT NULL,
	CONSTRAINT pk_Cabin PRIMARY KEY(id),
	CONSTRAINT unq_Cabin UNIQUE(type_Cabin)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Cabins_x_Route (
	id INT AUTO_INCREMENT NOT NULL,
	id_Cabin INT NOT NULL,
	id_Route INT NOT NULL,
	CONSTRAINT pk_CxR PRIMARY KEY (id),
	CONSTRAINT fk_IdCabin_CxR FOREIGN KEY(id_Cabin) REFERENCES Cabins(id),
	CONSTRAINT fk_IdRoute_CxR FOREIGN KEY(id_Route) REFERENCES Routes(id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS Prices (
	id INT AUTO_INCREMENT NOT NULL,
    id_Cabins_x_Route INT NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE,
    price FLOAT NOT NULL,
    CONSTRAINT pk_Price PRIMARY KEY(id),
    CONSTRAINT fk_id_CxR_Price FOREIGN KEY(id_Cabins_x_Route) REFERENCES Cabins_x_Route(id)
) ENGINE = InnoDB;

DELIMITER $$
CREATE TRIGGER tInsertNewPrice BEFORE INSERT
ON Prices
FOR EACH ROW
BEGIN
	IF EXISTS(SELECT * FROM Prices WHERE toDate IS null AND id_Cabins_x_Route = new.id_Cabins_x_Route) THEN
        UPDATE Prices SET toDate = new.fromDate WHERE toDate IS null AND id_Cabins_x_Route = new.id_Cabins_x_Route;
	END IF;
END;
$$