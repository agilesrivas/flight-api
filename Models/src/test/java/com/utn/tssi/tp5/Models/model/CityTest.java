package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CityTest extends TestCase{

    City city;
    City otherCity;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        this.city = new City(1, "Mar del Plata", "7600", state);
        this.otherCity = new City();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.city.toString(), "{name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherCity.toString(), "{name='null', iataCode='null', state=null}");
    }

    @Test
    public void testEqualsOK(){
        this.otherCity = this.city;
        assertTrue("Checking equals", this.city.equals(this.otherCity));
    }

    @Test
    public void testEqualsBad(){
        this.otherCity = new City("Mar Chiquita", "PPPP", null);

        assertFalse("Checking equals", this.city.equals(null));
        assertFalse("Checking equals", this.city.equals("String"));
        assertFalse("Checking equals", this.city.equals(this.otherCity));
        this.otherCity.setId(1);
        assertFalse("Checking equals", this.city.equals(this.otherCity));
        this.otherCity.setName("Mar del Plata");
        assertFalse("Checking equals", this.city.equals(this.otherCity));
        this.otherCity.setIataCode("7600");
        assertFalse("Checking equals", this.city.equals(this.otherCity));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.city.hashCode(), -1957527343);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherCity.hashCode(), 12005773);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.city.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherCity.validateNullEmpty());
        this.otherCity.setName("");
        assertTrue("Checking validateNullEmpty", this.otherCity.validateNullEmpty());
        this.otherCity.setName("A");

        assertTrue("Checking validateNullEmpty", this.otherCity.validateNullEmpty());
        this.otherCity.setIataCode("");
        assertTrue("Checking validateNullEmpty", this.otherCity.validateNullEmpty());
        this.otherCity.setIataCode("A");

        assertTrue("Checking validateNullEmpty", this.otherCity.validateNullEmpty());
        this.otherCity.setState(new State());
        assertTrue("Checking validateNullEmpty", this.otherCity.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.city.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherCity.validateNullEmptyIdentifier());
        this.otherCity.setIataCode("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherCity.validateNullEmptyIdentifier());
    }
}
