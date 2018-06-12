package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.City;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CityWrapperTest  extends TestCase {

    CityWrapper city;
    CityWrapper otherCity;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        State state = new State(1, "Buenos Aires", "BA", country);
        this.city = new CityWrapper(new City(1, "Mar del Plata", "7600", state));
        this.otherCity = new CityWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.city.toString(), "{name='Mar del Plata', iataCode='7600', state={name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherCity.toString(), "{name='null', iataCode='null', state={name='null', iataCode='null', country={name='null', isoCode='null'}}}");
    }

    @Test
    public void testEqualsOK(){
        this.otherCity = this.city;
        assertTrue("Checking equals", this.city.equals(this.otherCity));
    }

    @Test
    public void testEqualsBad(){
        this.otherCity = new CityWrapper(new City("Mar Chiquita", "PPPP", null));

        assertFalse("Checking equals", this.city.equals(null));
        assertFalse("Checking equals", this.city.equals("String"));
        assertFalse("Checking equals", this.city.equals(this.otherCity));
        this.otherCity.setName("Mar del Plata");
        assertFalse("Checking equals", this.city.equals(this.otherCity));
        this.otherCity.setIataCode("7600");
        assertFalse("Checking equals", this.city.equals(this.otherCity));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.city.hashCode(), -1980248266);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherCity.hashCode(), 755346);
    }

}
