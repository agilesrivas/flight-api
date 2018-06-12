package com.utn.WebService.wrapper;

import com.utn.WebService.wrapper.CountryWrapper;
import com.utn.tssi.tp5.Models.model.Country;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CountryWrapperTest extends TestCase {

    CountryWrapper country;
    CountryWrapper otherCountry;

    @Before
    public void setUp() {
        this.country = new CountryWrapper(new Country(1, "Argentina", "ARG"));
        this.otherCountry = new CountryWrapper(null);
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
        this.otherCountry = new CountryWrapper(new Country("Brasil", "BR"));

        assertFalse("Checking equals", this.country.equals(null));
        assertFalse("Checking equals", this.country.equals("String"));
        assertFalse("Checking equals", this.country.equals(this.otherCountry));
        this.otherCountry.setName("Argentina");
        assertFalse("Checking equals", this.country.equals(this.otherCountry));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.country.hashCode(), 2044189594);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherCountry.hashCode(), 10571);
    }
}
