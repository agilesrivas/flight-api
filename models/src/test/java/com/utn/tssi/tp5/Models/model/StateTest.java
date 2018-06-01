package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class StateTest extends TestCase{

    State state;
    State otherState;

    @Before
    public void setUp() {

        Country country = new Country(1, "Argentina", "ARG");
        this.state = new State(1, "Buenos Aires", "BA", country);
        this.otherState = new State("Córdoba", "COR", country);
    }

    @Test
    public void testToStringOK() {
        String value = this.state.toString();
<<<<<<< HEAD
        assertEquals("Checking toString", value, "State{name='Buenos Aires', iataCode='BA', country=Country{name='Argentina', isoCode='ARG'}}");
=======
        assertEquals("Checking toString", value, "{name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
    }

    @Test
    public void testToStringNull() {
        this.state.setCountry(null);
        String value = this.state.toString();

<<<<<<< HEAD
        assertEquals("Checking toString", value, "State{name='Buenos Aires', iataCode='BA', country=null}");
=======
        assertEquals("Checking toString", value, "{name='Buenos Aires', iataCode='BA', country=null}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
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
        this.otherState = this.state;

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
        assertEquals("Checking hashCode", value, 11082252);
    }
}
