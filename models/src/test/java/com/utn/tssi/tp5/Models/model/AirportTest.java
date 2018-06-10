package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class AirportTest extends TestCase{

    Airport airport;
    Airport otherAirport;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Mar del Plata", "7600", state);

        this.airport = new Airport(1, "Aeropuerto Astor Piazzolla", "MDQ", city, (float)-56.55, (float)-45.45);
        this.otherAirport = new Airport();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.airport.toString(), "{iataCode='MDQ', name='Aeropuerto Astor Piazzolla', city={name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=-56.55, longitude=-45.45}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherAirport.toString(), "{iataCode='null', name='null', city=null, latitude=0.0, longitude=0.0}");
    }

    @Test
    public void testEqualsOK(){
        this.otherAirport = this.airport;
        assertTrue("Checking equals", this.airport.equals(this.otherAirport));
    }

    @Test
    public void testEqualsBad(){
        this.otherAirport = new Airport("Pisterini", "PPPP", null, 0, 0);

        assertFalse("Checking equals", this.airport.equals(null));
        assertFalse("Checking equals", this.airport.equals("String"));
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setId(1);
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setName("Aeropuerto Astor Piazzolla");
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setIataCode("MDQ");
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setCity(this.airport.getCity());
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
        this.otherAirport.setLatitude((float)-56.55);
        assertFalse("Checking equals", this.airport.equals(this.otherAirport));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.airport.hashCode(), 1976432028);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherAirport.hashCode(), -459850354);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.airport.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherAirport.validateNullEmpty());
        this.otherAirport.setName("");
        assertTrue("Checking validateNullEmpty", this.otherAirport.validateNullEmpty());
        this.otherAirport.setName("A");

        assertTrue("Checking validateNullEmpty", this.otherAirport.validateNullEmpty());
        this.otherAirport.setIataCode("");
        assertTrue("Checking validateNullEmpty", this.otherAirport.validateNullEmpty());
        this.otherAirport.setIataCode("A");

        assertTrue("Checking validateNullEmpty", this.otherAirport.validateNullEmpty());
        this.otherAirport.setCity(new City());
        assertTrue("Checking validateNullEmpty", this.otherAirport.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.airport.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherAirport.validateNullEmptyIdentifier());
        this.otherAirport.setIataCode("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherAirport.validateNullEmptyIdentifier());
    }
}
