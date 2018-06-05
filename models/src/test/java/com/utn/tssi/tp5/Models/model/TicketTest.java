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
        Cabin cabinA = new Cabin(1, "Económica");
        Cabin cabinB = new Cabin(1, "VIP");
        Price priceA = new Price(1, (float)1.12, "25/06/2018", null, true, cabinA);
        Price priceB = new Price((float)2.42, "29/06/2018", null, true, cabinB);
        User user = new User(1, "pepe", "pompin");

        this.ticket = new Ticket(1, flight, priceA, user);
        this.otherTicket = new Ticket(flight, priceB, user);
    }

    @Test
    public void testToStringOK() {
        String value = this.ticket.toString();
        assertEquals("Checking toString", value, "{flight={route={airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International Airport', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}, price={price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económica'}}, user={name='pepe', password='pompin'}, date='21/05/2018', totalPrice=56.00000023841858}");
    }

    @Test
    public void testToStringNull() {
        this.ticket.setFlight(null);
        this.ticket.calculateTotalPrice();

        String value = this.ticket.toString();

        assertEquals("Checking toString", value, "{flight=null, price={price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económica'}}, user={name='pepe', password='pompin'}, date='21/05/2018', totalPrice=0.0}");
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

        boolean value = this.ticket.equals(new Ticket());
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
        assertEquals("Checking hashCode", value, 1866296128);
    }

    @Test
    public void testHashCodeOneNull() {
        this.ticket.setFlight(null);
        this.ticket.calculateTotalPrice();

        int value = this.ticket.hashCode();
        assertEquals("Checking hashCode", value, 129905625);
    }

    @Test
    public void testHashCodeAllNull() {
        this.ticket = new Ticket();

        int value = this.ticket.hashCode();
        assertEquals("Checking hashCode", value, -1204802926);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.ticket.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.ticket.setFlight(null);

        boolean value = this.ticket.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.ticket.setDate("");

        boolean value = this.ticket.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        boolean value = this.ticket.validateNullEmptyIdentifier();
        assertFalse("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        this.ticket.setFlight(null);

        boolean value = this.ticket.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeEmpty() {
        this.ticket.setDate("");

        boolean value = this.ticket.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }
}
