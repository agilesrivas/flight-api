package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


public class TicketTest extends TestCase{

    Ticket ticket;
    Ticket otherTicket;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Buenos Aires", "CABA", state);
        Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        Airport airportEnd = new Airport(2, "Ezeiza International Airport", "EZE", city, (float)24.22, (float)107.58);
        Route route = new Route(1, airportBegin, airportEnd, 50, 3);
        Flight flight = new Flight(1, route, "21/05/2018");
        Cabin cabinA = new Cabin(1, "Económica", 1.12);
        Cabin cabinB = new Cabin(1, "VIP", 1.59);

        this.ticket = new Ticket(1, flight, cabinA);
        this.otherTicket = new Ticket(flight, cabinB);
    }

    @Test
    public void testToStringOK() {
        String value = this.ticket.toString();
<<<<<<< HEAD
        assertEquals("Checking toString", value, "{flight={route={airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International Airport', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}, cabin={name='Económica', priceKm=1.12}, date='21/05/2018', totalPrice=56.00000000000001}");
=======
<<<<<<< HEAD
        assertEquals("Checking toString", value, "Ticket{flight=Flight{route=Route{airportBegin=Airport{iataCode='AEP', name='Jorge Newbery', city=City{name='Buenos Aires', iataCode='CABA', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd=Airport{iataCode='EZE', name='Ezeiza International Airport', city=City{name='Buenos Aires', iataCode='CABA', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}, cabin=Cabin{name='Económica', priceKm=1.12}, date='21/05/2018', totalPrice=56.00000000000001}");
=======
        assertEquals("Checking toString", value, "{flight={route={airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International Airport', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}, cabin={name='Económica', priceKm=1.12}, date='21/05/2018', totalPrice=56.00000000000001}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Test
    public void testToStringNull() {
        this.ticket.setFlight(null);
        this.ticket.calculateTotalPrice();

        String value = this.ticket.toString();

<<<<<<< HEAD
        assertEquals("Checking toString", value, "{flight=null, cabin={name='Económica', priceKm=1.12}, date='21/05/2018', totalPrice=0.0}");
=======
<<<<<<< HEAD
        assertEquals("Checking toString", value, "Ticket{flight=null, cabin=Cabin{name='Económica', priceKm=1.12}, date='21/05/2018', totalPrice=0.0}");
=======
        assertEquals("Checking toString", value, "{flight=null, cabin={name='Económica', priceKm=1.12}, date='21/05/2018', totalPrice=0.0}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.ticket.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.ticket.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        this.otherTicket.setId(0);
        this.otherTicket.setFlight(null);
        this.otherTicket.setCabin(null);
        this.otherTicket.setDate(null);
        this.otherTicket.setTotalPrice(0);

        boolean value = this.ticket.equals(this.otherTicket);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.ticket.equals(this.otherTicket);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherTicket = this.ticket;

        boolean value = this.ticket.equals(this.otherTicket);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.ticket.hashCode();
        assertEquals("Checking hashCode", value, -1414432613);
    }

    @Test
    public void testHashCodeOneNull() {
        this.ticket.setFlight(null);
        this.ticket.calculateTotalPrice();

        int value = this.ticket.hashCode();
        assertEquals("Checking hashCode", value, 746312050);
    }

    @Test
    public void testHashCodeAllNull() {
        this.ticket.setId(0);
        this.ticket.setFlight(null);
        this.ticket.setCabin(null);
        this.ticket.setDate(null);
        this.ticket.setTotalPrice(0);

        int value = this.ticket.hashCode();
        assertEquals("Checking hashCode", value, 515324718);
    }
}
