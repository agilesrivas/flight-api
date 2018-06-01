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
        this.otherFlight = new Flight(route, "21/05/2018");
    }

    @Test
    public void testToStringOK() {
        String value = this.flight.toString();
<<<<<<< HEAD
        assertEquals("Checking toString", value, "Flight{route=Route{airportBegin=Airport{iataCode='AEP', name='Jorge Newbery', city=City{name='Buenos Aires', iataCode='CABA', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd=Airport{iataCode='EZE', name='Ezeiza International Airport', city=City{name='Buenos Aires', iataCode='CABA', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}");
=======
        assertEquals("Checking toString", value, "{route={airportBegin={iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}, airportEnd={iataCode='EZE', name='Ezeiza International Airport', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=24.22, longitude=107.58}, distance=50, estimatedTime=3}, date='21/05/2018'}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @Test
    public void testToStringNull() {
        this.flight.setRoute(null);
        String value = this.flight.toString();

<<<<<<< HEAD
        assertEquals("Checking toString", value, "Flight{route=null, date='21/05/2018'}");
=======
        assertEquals("Checking toString", value, "{route=null, date='21/05/2018'}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.flight.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.flight.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        this.otherFlight.setId(0);
        this.otherFlight.setRoute(null);
        this.otherFlight.setDate(null);

        boolean value = this.flight.equals(this.otherFlight);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.flight.equals(this.otherFlight);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherFlight = this.flight;

        boolean value = this.flight.equals(this.otherFlight);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.flight.hashCode();
        assertEquals("Checking hashCode", value, 1986012847);
    }

    @Test
    public void testHashCodeOneNull() {
        this.flight.setRoute(null);
        int value = this.flight.hashCode();
        assertEquals("Checking hashCode", value, 1914309871);
    }

    @Test
    public void testHashCodeAllNull() {
        this.flight.setId(0);
        this.flight.setRoute(null);
        this.flight.setDate(null);

        int value = this.flight.hashCode();
        assertEquals("Checking hashCode", value, 506447);
    }
}
