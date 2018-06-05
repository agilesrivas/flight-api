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

        this.city = new City(1, "Mar del Plata", "MDQ", state);
        this.otherCity = new City("La Plata", "LP", state);
    }

    @Test
    public void testToStringOK() {
        String value = this.city.toString();
        assertEquals("Checking toString", value, "{name='Mar del Plata', iataCode='MDQ', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}");
    }

    @Test
    public void testToStringNull() {
        this.city.setState(null);
        String value = this.city.toString();

        assertEquals("Checking toString", value, "{name='Mar del Plata', iataCode='MDQ', state=null}");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.city.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.city.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){

        boolean value = this.city.equals(new City());
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.city.equals(this.otherCity);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherCity = this.city;

        boolean value = this.city.equals(this.otherCity);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.city.hashCode();
        assertEquals("Checking hashCode", value, -2007615562);
    }

    @Test
    public void testHashCodeOneNull() {
        this.city.setIataCode(null);
        int value = this.city.hashCode();
        assertEquals("Checking hashCode", value, -2009977328);
    }

    @Test
    public void testHashCodeAllNull() {
        this.city = new City();

        int value = this.city.hashCode();
        assertEquals("Checking hashCode", value, 12005773);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.city.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.city.setName(null);

        boolean value = this.city.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.city.setName("");

        boolean value = this.city.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        boolean value = this.city.validateNullEmptyIdentifier();
        assertFalse("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        this.city.setIataCode(null);

        boolean value = this.city.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeEmpty() {
        this.city.setIataCode("");

        boolean value = this.city.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }
}
