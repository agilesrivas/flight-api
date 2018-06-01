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
<<<<<<< HEAD
        assertEquals("Checking toString", value, "City{name='Mar del Plata', iataCode='MDQ', state=State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}}");
=======
        assertEquals("Checking toString", value, "{name='Mar del Plata', iataCode='MDQ', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @Test
    public void testToStringNull() {
        this.city.setState(null);
        String value = this.city.toString();

<<<<<<< HEAD
        assertEquals("Checking toString", value, "City{name='Mar del Plata', iataCode='MDQ', state=null}");
=======
        assertEquals("Checking toString", value, "{name='Mar del Plata', iataCode='MDQ', state=null}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
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
        this.otherCity.setId(0);
        this.otherCity.setName(null);
        this.otherCity.setState(null);
        this.otherCity.setIataCode(null);

        boolean value = this.city.equals(this.otherCity);
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
        this.city.setId(0);
        this.city.setName(null);
        this.city.setIataCode(null);
        this.city.setState(null);

        int value = this.city.hashCode();
        assertEquals("Checking hashCode", value, 12005773);
    }
}
