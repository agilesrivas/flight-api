package com.utn.tssi.tp5.ApiRestVuelos.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class StateTest extends TestCase{

    State state;
    State otherState;

    @Before
    public void setUp() {
        /*Country countryMock = Mockito.mock(Country.class);
        when(countryMock.getId()).thenReturn(1L);
        when(countryMock.getName()).thenReturn("Argentina");
        when(countryMock.getIsoCode()).thenReturn("ARG");

        when(countryMock.toString()).thenReturn("Argentina (ARG)");
        when(countryMock.hashCode()).thenReturn(2044507685);*/

        Country country = new Country(1, "Argentina", "ARG");
        this.state = new State(1, "Buenos Aires", "BA", country);
        this.otherState = new State("Córdoba", "COR", country);
    }

    @Test
    public void testToStringOK() {
        String value = this.state.toString();
        assertEquals("Checking toString", value, "Buenos Aires (BA) - Argentina (ARG)");
    }

    @Test
    public void testToStringNull() {
        this.state.setName(null);
        String value = this.state.toString();

        assertEquals("Checking toString", value, "null (BA) - Argentina (ARG)");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.state.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.state.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        this.otherState.setId(0);
        this.otherState.setName(null);
        this.otherState.setCountry(null);
        this.otherState.setIataCode(null);

        boolean value = this.state.equals(this.otherState);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.state.equals(this.otherState);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherState.setId(1);
        this.otherState.setName("Buenos Aires");
        this.otherState.setIataCode("BA");

        boolean value = this.state.equals(this.otherState);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 1707458729);
    }

    @Test
    public void testHashCodeOneNull() {
        this.state.setIataCode(null);
        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 1707393288);
    }

    @Test
    public void testHashCodeAllNull() {
        this.state.setId(0);
        this.state.setName(null);
        this.state.setIataCode(null);
        this.state.setCountry(null);

        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 2055589937);
    }
}
