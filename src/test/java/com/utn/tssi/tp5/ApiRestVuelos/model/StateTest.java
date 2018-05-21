package com.utn.tssi.tp5.ApiRestVuelos.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class StateTest extends TestCase{

    State state;

    @Before
    public void setUp() {

        this.state = new State();
        this.state.setId(1);
        this.state.setIataCode("BA");
        this.state.setName("Buenos Aires");
        this.state.setCountry(new Country(1, "Argentina", "ARG"));
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
        State otherstate = new State(null, null, null);
        boolean value = this.state.equals(otherstate);

        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        State otherstate = new State(2, "Brasil", "BR", new Country(1, "Brasil", "BR"));
        boolean value = this.state.equals(otherstate);

        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        State otherstate = new State(1, "Buenos Aires", "BA", new Country(1, "Argentina", "ARG"));
        boolean value = this.state.equals(otherstate);

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

        int value = this.state.hashCode();
        assertEquals("Checking hashCode", value, 2055589937);
    }
}
