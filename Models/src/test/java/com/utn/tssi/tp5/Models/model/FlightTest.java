package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class FlightTest extends TestCase{

    Flight flight;
    Flight otherFlight;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Buenos Aires", "CABA", state);
        Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        Airport airportEnd = new Airport(2, "Ezeiza International Airport", "EZE", city, (float)24.22, (float)107.58);
        Route route = new Route(1, airportBegin, airportEnd, 50, 3);

        this.flight = new Flight(1, route, "21/05/2018");
        this.otherFlight = new Flight();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.flight.toString(), "{route={airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International Airport', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherFlight.toString(), "{route=null, date='null'}");
    }

    @Test
    public void testEqualsOK(){
        this.otherFlight = this.flight;
        assertTrue("Checking equals", this.flight.equals(this.otherFlight));
    }

    @Test
    public void testEqualsBad(){
        this.otherFlight = new Flight(new Route(), "22/05/2018");

        assertFalse("Checking equals", this.flight.equals(null));
        assertFalse("Checking equals", this.flight.equals("String"));
        assertFalse("Checking equals", this.flight.equals(this.otherFlight));
        this.otherFlight.setId(1);
        assertFalse("Checking equals", this.flight.equals(this.otherFlight));
        this.otherFlight.setRoute(this.flight.getRoute());
        assertFalse("Checking equals", this.flight.equals(this.otherFlight));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.flight.hashCode(), 1986012847);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherFlight.hashCode(), 506447);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.flight.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherFlight.validateNullEmpty());
        this.otherFlight.setRoute(new Route());
        assertTrue("Checking validateNullEmpty", this.otherFlight.validateNullEmpty());

        assertTrue("Checking validateNullEmpty", this.otherFlight.validateNullEmpty());
        this.otherFlight.setDate("");
        assertTrue("Checking validateNullEmpty", this.otherFlight.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.flight.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherFlight.validateNullEmptyIdentifier());
        this.otherFlight.setRoute(new Route());
        assertTrue("Checking validateNullEmptyIdentifier", this.otherFlight.validateNullEmptyIdentifier());
        this.otherFlight.setDate("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherFlight.validateNullEmptyIdentifier());
    }
}
