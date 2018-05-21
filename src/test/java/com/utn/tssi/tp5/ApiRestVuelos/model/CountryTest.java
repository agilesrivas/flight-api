package com.utn.tssi.tp5.ApiRestVuelos.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CountryTest extends TestCase{

    Country country;


    @Before
    public void setUp() {
        this.country = new Country();
        this.country.setId(1);
        this.country.setIsoCode("ARG");
        this.country.setName("Argentina");
    }

    @Test
    public void testToStringOK() {
        String value = this.country.toString();
        assertEquals("Checking toString", value, "Argentina (ARG)");
    }

    @Test
    public void testToStringNull() {
        this.country.setName(null);
        String value = this.country.toString();

        assertEquals("Checking toString", value, "null (ARG)");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.country.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.country.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        Country otherCountry = new Country(null, null);
        boolean value = this.country.equals(otherCountry);

        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        Country otherCountry = new Country(2, "Brasil", "BR");
        boolean value = this.country.equals(otherCountry);

        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        Country otherCountry = new Country(1, "Argentina", "ARG");
        boolean value = this.country.equals(otherCountry);

        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.country.hashCode();
        assertEquals("Checking hashCode", value, 2044507685);
    }

    @Test
    public void testHashCodeOneNull() {
        this.country.setIsoCode(null);
        int value = this.country.hashCode();
        assertEquals("Checking hashCode", value, 2044442607);
    }

    @Test
    public void testHashCodeAllNull() {
        this.country.setId(0);
        this.country.setName(null);
        this.country.setIsoCode(null);

        int value = this.country.hashCode();
        assertEquals("Checking hashCode", value, 327701);
    }
}
