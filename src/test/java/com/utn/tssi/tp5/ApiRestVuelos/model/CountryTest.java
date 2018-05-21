package com.utn.tssi.tp5.ApiRestVuelos.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CountryTest extends TestCase{

    Country country;
    Country otherCountry;

    @Before
    public void setUp() {
        this.country = new Country(1, "Argentina", "ARG");
        this.otherCountry = new Country("Brasil", "BR");
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
        this.otherCountry.setId(0);
        this.otherCountry.setName(null);
        this.otherCountry.setIsoCode(null);

        boolean value = this.country.equals(otherCountry);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.country.equals(this.otherCountry);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherCountry.setId(1);
        this.otherCountry.setName("Argentina");
        this.otherCountry.setIsoCode("ARG");

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
