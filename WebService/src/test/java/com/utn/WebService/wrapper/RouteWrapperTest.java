package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class RouteWrapperTest extends TestCase {

    RouteWrapper route;
    RouteWrapper otherRoute;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Mar del Plata", "7600", state);
        Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        Airport airportEnd = new Airport(2, "Ezeiza International route", "EZE", city, (float)24.22, (float)107.58);

        this.route = new RouteWrapper(new Route(1, airportBegin, airportEnd, 50, 3));
        this.otherRoute = new RouteWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.route.toString(), "{airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International route', city={name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherRoute.toString(), "{airportBegin={iataCode='null', name='null', city={name='null', iataCode='null', state={name='null', iataCode='null', country={name='null', isoCode='null'}}}, latitude=0.0, longitude=0.0}, airportEnd={iataCode='null', name='null', city={name='null', iataCode='null', state={name='null', iataCode='null', country={name='null', isoCode='null'}}}, latitude=0.0, longitude=0.0}, distance=0, estimatedTime=0}");
    }

    @Test
    public void testEqualsOK(){
        this.otherRoute = this.route;
        assertTrue("Checking equals", this.route.equals(this.otherRoute));
    }

    @Test
    public void testEqualsBad(){
        this.otherRoute = new RouteWrapper(new Route(new Airport(), new Airport(), 0, 0));

        assertFalse("Checking equals", this.route.equals(null));
        assertFalse("Checking equals", this.route.equals("String"));
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setAirportWrapperBegin(this.route.getAirportWrapperBegin());
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setAirportWrapperEnd(this.route.getAirportWrapperEnd());
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
        this.otherRoute.setDistance(50);
        assertFalse("Checking equals", this.route.equals(this.otherRoute));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.route.hashCode(), 772178204);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherRoute.hashCode(), 656382223);
    }

}
