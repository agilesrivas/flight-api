package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Airport;
import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class AirportWrapperTest extends TestCase {

    AirportWrapper airport;
    AirportWrapper otherAirport;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Mar del Plata", "7600", state);

        this.airport = new AirportWrapper(new Airport(1, "Aeropuerto Astor Piazzolla", "MDQ", city, (float)-56.55, (float)-45.45));
        this.otherAirport = new AirportWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.airport.toString(), "{iataCode='MDQ', name='Aeropuerto Astor Piazzolla', city={name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=-56.55, longitude=-45.45}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherAirport.toString(), "{iataCode='null', name='null', city={name='null', iataCode='null', state={name='null', iataCode='null', country={name='null', isoCode='null'}}}, latitude=0.0, longitude=0.0}");
    }

    @Test
    public void testEqualsOK(){
        this.otherAirport = this.airport;
        assertTrue("Checking equals", this.airport.equals(this.otherAirport));
    }

    @Test
    public void testEqualsBad(){
        this.otherAirport = new AirportWrapper(new Airport("Pisterini", "PPPP", null, 0, 0));

        assertFalse("Checking equals", this.airport.equals(null));
        assertFalse("Checking equals", this.airport.equals("String"));
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setName("Aeropuerto Astor Piazzolla");
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setIataCode("MDQ");
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setCityWrapper(this.airport.getCityWrapper());
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setLatitude((float)-56.55);
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.airport.hashCode(), -1846476474);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherAirport.hashCode(), 1126695620);
    }

}
