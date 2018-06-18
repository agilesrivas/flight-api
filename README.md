# [Trabajo Practico 5](https://github.com/gianfrancostabile/Trabajo-Practico-5)

## Travis: 
   - Models - [![Build Status](https://travis-ci.org/gianfrancostabile/Trabajo-Practico-5.svg?branch=master)](https://travis-ci.org/gianfrancostabile/Trabajo-Practico-5)
  - ApiRest - **(Sin implementar)**
  - WebService - **(Sin implementar)**
  
## Integrantes:
  * [Alejandro Giles Rivas](https://github.com/Alekano)
  * [Gian Franco Stabile](https://github.com/gianfrancostabile)

## [Diagrama UML Modelos](https://github.com/gianfrancostabile/Trabajo-Practico-5/blob/master/Util/Diagrama%20UML.pdf)

## [Base de Datos](https://github.com/gianfrancostabile/Trabajo-Practico-5/blob/master/Util/SQL.sql) (MYSQL):
![DER SQL](https://github.com/gianfrancostabile/Trabajo-Practico-5/blob/master/Util/DER%20SQL.png)

## Relaciones del Projecto:
![Relaciones Projecto](https://raw.githubusercontent.com/gianfrancostabile/Trabajo-Practico-5/master/Util/Relaciones.png)
<br />
La conexión de la Web Service a la Api Rest se realiza con la ayuda de RestTemplate. **RestTemplate** es la clase que ofrece Spring para el acceso desde la parte cliente a Servicios REST.

## Endpoints utilizados:
  - ApiRest (puerto utilizado 25100):
    - Country: 
      - /country/ (POST) - ADD
      - /country/ (PUT) - UPDATE
      - /country/ (DELETE) - DELETE
      - /country/ (GET) - GET ALL
      - /country (GET) - GET ONE
    - State: 
      - /state/ (POST) - ADD
      - /state/ (PUT) - UPDATE
      - /state/ (DELETE) - DELETE
      - /state/ (GET) - GET ALL
      - /state (GET) - GET ONE
    - City: 
      - /city/ (POST) - ADD
      - /city/ (PUT) - UPDATE
      - /city/ (DELETE) - DELETE
      - /city/ (GET) - GET ALL
      - /city (GET) - GET ONE
    - Airport: 
      - /airport/ (POST) - ADD
      - /airport/ (PUT) - UPDATE
      - /airport/ (DELETE) - DELETE
      - /airport/ (GET) - GET ALL
      - /airport (GET) - GET ONE
    - Route: 
      - /route/ (POST) - ADD
      - /route/ (PUT) - UPDATE
      - /route/ (DELETE) - DELETE
      - /route/ (GET) - GET ALL
      - /route/{iata} (GET) - GET BY START AIRPORT
      - /route (GET) - GET ONE
    - Flight: 
      - /flight/ (POST) - ADD
      - /flight/ (PUT) - UPDATE
      - /flight/ (DELETE) - DELETE
      - /flight/ (GET) - GET ALL
      - /flight/{from_date}/{to_date} (GET) - GET BY DATES
      - /flight (GET) - GET ONE
    - Cabin: 
      - /cabin/ (POST) - ADD
      - /cabin/ (PUT) - UPDATE
      - /cabin/ (DELETE) - DELETE
      - /cabin/ (GET) - GET ALL
      - /cabin (GET) - GET ONE
    - Price: 
      - /price/ (POST) - ADD
      - /price/ (PUT) - UPDATE
      - /price/ (DELETE) - DELETE
      - /price/ (GET) - GET ALL
      - /price (GET) - GET ONE
    - User: 
      - /user/ (POST) - ADD
      - /user (POST) - GET ONE
    - Ticket: 
      - /ticket/ (POST) - ADD
      - /ticket/ (PUT) - UPDATE
      - /ticket/ (DELETE) - DELETE
      - /ticket/ (GET) - GET ALL
      - /ticket (GET) - GET ONE
   <br />
   
  - WebService (puerto utilizado 25101):
    - User: 
      - /user (GET) - USER VIEW
      - /user/register (POST) - REGISTER
      - /user/login (POST) - LOGIN
      - /user/logout (GET) - LOGOUT
    - Main: 
      - /index (GET) - WEB INDEX
    - Ticket: 
      - /ticket (GET) - REQUEST A TICKET
