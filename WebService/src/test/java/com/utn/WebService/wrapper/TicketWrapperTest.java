package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TicketWrapperTest extends TestCase {

    TicketWrapper ticket;
    TicketWrapper otherTicket;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Buenos Aires", "CABA", state);
        Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        Airport airportEnd = new Airport(2, "Ezeiza International Airport", "EZE", city, (float)24.22, (float)107.58);
        Route route = new Route(1, airportBegin, airportEnd, 50, 3);
        Flight flight = new Flight(1, route, "21/05/2018");
        Cabin cabinA = new Cabin(1, "Económica");
        Cabin cabinB = new Cabin(1, "VIP");
        Price priceA = new Price(1, (float)1.12, "25/06/2018", null, true, cabinA);
        Price priceB = new Price((float)2.42, "29/06/2018", null, true, cabinB);
        User user = new User(1, "pepe", "pompin");

        this.ticket = new TicketWrapper(new Ticket(1, flight, priceA, user));
        this.otherTicket = new TicketWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.ticket.toString(), "{flight={route={airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International Airport', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}, price={price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económica'}}, user={name='pepe', password='pompin'}, date='21/05/2018', totalPrice=56.00000023841858}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherTicket.toString(), "{flight={route={airportBegin={iataCode='null', name='null', city={name='null', iataCode='null', state={name='null', iataCode='null', country={name='null', isoCode='null'}}}, latitude=0.0, longitude=0.0}, airportEnd={iataCode='null', name='null', city={name='null', iataCode='null', state={name='null', iataCode='null', country={name='null', isoCode='null'}}}, latitude=0.0, longitude=0.0}, distance=0, estimatedTime=0}, date='null'}, price={price=0.0, fromDate='null', toDate='null', state_bool=false, cabin={name='null'}}, user={name='null', password='null'}, date='null', totalPrice=0.0}");
    }

    @Test
    public void testEqualsOK(){
        this.otherTicket = this.ticket;
        assertTrue("Checking equals", this.ticket.equals(this.otherTicket));
    }

    @Test
    public void testEqualsBad(){
        this.otherTicket = new TicketWrapper(new Ticket(new Flight(), new Price(), new User()));

        assertFalse("Checking equals", this.ticket.equals(null));
        assertFalse("Checking equals", this.ticket.equals("String"));
        assertFalse("Checking equals", this.ticket.equals(this.otherTicket));
        assertFalse("Checking equals", this.ticket.equals(this.otherTicket));
        this.otherTicket.setFlightWrapper(this.ticket.getFlightWrapper());
        assertFalse("Checking equals", this.ticket.equals(this.otherTicket));
        this.otherTicket.setPriceWrapper(this.ticket.getPriceWrapper());
        assertFalse("Checking equals", this.ticket.equals(this.otherTicket));
        this.otherTicket.setUserWrapper(this.ticket.getUserWrapper());
        assertFalse("Checking equals", this.ticket.equals(this.otherTicket));
        this.otherTicket.setDate(this.ticket.getFlightWrapper().getDate());
        assertFalse("Checking equals", this.ticket.equals(this.otherTicket));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.ticket.hashCode(), 725899613);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherTicket.hashCode(), -1426917756);
    }

}
