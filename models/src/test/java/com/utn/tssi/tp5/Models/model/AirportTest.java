package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class AirportTest extends TestCase {

    Airport airport;
    Airport otherAirport;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        City city = new City(1, "Buenos Aires", "CABA", state);

        this.airport = new Airport(1, "Jorge Newbery", "AEP", city, (float)23.14, (float)108.11);
        this.otherAirport = new Airport("Ezeiza International Airport", "EZE", city, (float)24.22, (float)107.58);
    }

    @Test
    public void testToStringOK() {
        String value = this.airport.toString();
        assertEquals("Checking toString", value, "{iataCode='AEP', name='Jorge Newbery', city={name='Buenos Aires', iataCode='CABA', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}, latitude=23.14, longitude=108.11}");
    }

    @Test
    public void testToStringNull() {
        this.airport.setCity(null);
        String value = this.airport.toString();

        assertEquals("Checking toString", value, "{iataCode='AEP', name='Jorge Newbery', city=null, latitude=23.14, longitude=108.11}");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.airport.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.airport.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){

        boolean value = this.airport.equals(new Airport());
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.airport.equals(this.otherAirport);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherAirport = this.airport;

        boolean value = this.airport.equals(this.otherAirport);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.airport.hashCode();
        assertEquals("Checking hashCode", value, -1606017605);
    }

    @Test
    public void testHashCodeOneNull() {
        this.airport.setIataCode(null);
        int value = this.airport.hashCode();
        assertEquals("Checking hashCode", value, 761948647);
    }

    @Test
    public void testHashCodeAllNull() {
        this.airport = new Airport();

        int value = this.airport.hashCode();
        assertEquals("Checking hashCode", value, -459850354);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.airport.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.airport.setName(null);

        boolean value = this.airport.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.airport.setName("");

        boolean value = this.airport.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        boolean value = this.airport.validateNullEmptyIdentifier();
        assertFalse("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        this.airport.setIataCode(null);

        boolean value = this.airport.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeEmpty() {
        this.airport.setIataCode("");

        boolean value = this.airport.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }
}
