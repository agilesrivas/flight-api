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
        City city = new City(1, "Buenos Aires", "CABA", state);
        Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        Airport airportEnd = new Airport(2, "Ezeiza International Airport", "EZE", city, (float)24.22, (float)107.58);

        this.route = new Route(1, airportBegin, airportEnd, 50, 3);
        this.otherRoute = new Route(airportEnd, airportBegin, 50, 3);
    }

    @Test
    public void testToStringOK() {
        String value = this.route.toString();
        assertEquals("Checking toString", value, "Route{airportBegin=Airport{iataCode='AEP', name='Jorge Newbery', city=City{name='Buenos Aires', iataCode='CABA', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd=Airport{iataCode='EZE', name='Ezeiza International Airport', city=City{name='Buenos Aires', iataCode='CABA', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}");
    }

    @Test
    public void testToStringNull() {
        this.route.setAirportBegin(null);
        this.route.setAirportEnd(null);
        String value = this.route.toString();

        assertEquals("Checking toString", value, "Route{airportBegin=null, airportEnd=null, distance=50, estimatedTime=3}");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.route.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.route.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        this.otherRoute.setId(0);
        this.otherRoute.setAirportBegin(null);
        this.otherRoute.setAirportEnd(null);
        this.otherRoute.setDistance(0);
        this.otherRoute.setEstimatedTime(0);

        boolean value = this.route.equals(this.otherRoute);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.route.equals(this.otherRoute);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherRoute = this.route;

        boolean value = this.route.equals(this.otherRoute);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.route.hashCode();
        assertEquals("Checking hashCode", value, 833596992);
    }

    @Test
    public void testHashCodeOneNull() {
        this.route.setAirportEnd(null);
        int value = this.route.hashCode();
        assertEquals("Checking hashCode", value, 1495569224);
    }

    @Test
    public void testHashCodeAllNull() {
        this.route.setId(0);
        this.route.setAirportBegin(null);
        this.route.setAirportEnd(null);
        this.route.setDistance(0);
        this.route.setEstimatedTime(0);

        int value = this.route.hashCode();
        assertEquals("Checking hashCode", value, 429437265);
    }
}
