package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class RouteTest extends TestCase {

    Route route;
    Route otherRoute;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Mar del Plata", "7600", state);
        Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        Airport airportEnd = new Airport(2, "Ezeiza International route", "EZE", city, (float)24.22, (float)107.58);

        this.route = new Route(1, airportBegin, airportEnd, 50, 3);
        this.otherRoute = new Route();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.route.toString(), "{airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International route', city={name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherRoute.toString(), "{airportBegin=null, airportEnd=null, distance=0, estimatedTime=0}");
    }

    @Test
    public void testEqualsOK(){
        this.otherRoute = this.route;
        assertTrue("Checking equals", this.route.equals(this.otherRoute));
    }

    @Test
    public void testEqualsBad(){
        this.otherRoute = new Route(new Airport(), new Airport(), 0, 0);

        assertFalse("Checking equals", this.route.equals(null));
        assertFalse("Checking equals", this.route.equals("String"));
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setId(1);
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setAirportBegin(this.route.getAirportBegin());
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setAirportEnd(this.route.getAirportEnd());
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setDistance(50);
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.route.hashCode(), -1126043394);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherRoute.hashCode(), 429437265);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.route.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherRoute.validateNullEmpty());
        this.otherRoute.setAirportBegin(new Airport());
        assertTrue("Checking validateNullEmpty", this.otherRoute.validateNullEmpty());
        this.otherRoute.setAirportBegin(this.route.getAirportBegin());

        assertTrue("Checking validateNullEmpty", this.otherRoute.validateNullEmpty());
        this.otherRoute.setAirportEnd(new Airport());
        assertTrue("Checking validateNullEmpty", this.otherRoute.validateNullEmpty());
        this.otherRoute.setAirportEnd(this.route.getAirportEnd());

        this.otherRoute.setDistance(-1);
        assertTrue("Checking validateNullEmpty", this.otherRoute.validateNullEmpty());
        this.otherRoute.setDistance(0);
        this.otherRoute.setEstimatedTime(-1);
        assertTrue("Checking validateNullEmpty", this.otherRoute.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.route.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherRoute.validateNullEmptyIdentifier());
        this.otherRoute.setAirportBegin(new Airport());
        this.otherRoute.getAirportBegin().setIataCode("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherRoute.validateNullEmptyIdentifier());
        this.otherRoute.getAirportBegin().setIataCode("A");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherRoute.validateNullEmptyIdentifier());
        this.otherRoute.setAirportEnd(new Airport());
        this.otherRoute.getAirportEnd().setIataCode("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherRoute.validateNullEmptyIdentifier());
    }
}
