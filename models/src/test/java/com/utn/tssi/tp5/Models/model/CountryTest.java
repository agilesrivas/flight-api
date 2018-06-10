package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CountryTest extends TestCase{

    Country country;
    Country otherCountry;

    @Before
    public void setUp() {
        this.country = new Country(1, "Argentina", "ARG");
        this.otherCountry = new Country();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.country.toString(), "{name='Argentina', isoCode='ARG'}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherCountry.toString(), "{name='null', isoCode='null'}");
    }

    @Test
    public void testEqualsOK(){
        this.otherCountry = this.country;
        assertTrue("Checking equals", this.country.equals(otherCountry));
    }

    @Test
    public void testEqualsBad(){
        this.otherCountry = new Country("Brasil", "BR");

        assertFalse("Checking equals", this.country.equals(null));
        assertFalse("Checking equals", this.country.equals("String"));
        assertFalse("Checking equals", this.country.equals(this.otherCountry));
        this.otherCountry.setId(1);
        assertFalse("Checking equals", this.country.equals(this.otherCountry));
        this.otherCountry.setName("Argentina");
        assertFalse("Checking equals", this.country.equals(this.otherCountry));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.country.hashCode(), 2044507685);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherCountry.hashCode(), 327701);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.country.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherCountry.validateNullEmpty());
        this.otherCountry.setName("   ");
        assertTrue("Checking validateNullEmpty", this.otherCountry.validateNullEmpty());
        this.otherCountry.setName("A");

        assertTrue("Checking validateNullEmpty", this.otherCountry.validateNullEmpty());
        this.otherCountry.setIsoCode("    ");
        assertTrue("Checking validateNullEmpty", this.otherCountry.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.country.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherCountry.validateNullEmptyIdentifier());
        this.otherCountry.setIsoCode("   ");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherCountry.validateNullEmptyIdentifier());
    }
}
