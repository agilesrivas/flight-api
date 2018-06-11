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
        this.otherState = new State();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.state.toString(), "{name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherState.toString(), "{name='null', iataCode='null', country=null}");
    }

    @Test
    public void testEqualsOK(){
        this.otherState = this.state;
        assertTrue("Checking equals", this.state.equals(this.otherState));
    }

    @Test
    public void testEqualsBad(){
        this.otherState = new State("La Pampa", "PPPP", null);

        assertFalse("Checking equals", this.state.equals(null));
        assertFalse("Checking equals", this.state.equals("String"));
        assertFalse("Checking equals", this.state.equals(this.otherState));
        this.otherState.setId(1);
        assertFalse("Checking equals", this.state.equals(this.otherState));
        this.otherState.setName("Buenos Aires");
        assertFalse("Checking equals", this.state.equals(this.otherState));
        this.otherState.setIataCode("BA");
        assertFalse("Checking equals", this.state.equals(this.otherState));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.state.hashCode(), 1707458729);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherState.hashCode(), 11082252);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.state.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherState.validateNullEmpty());
        this.otherState.setName("");
        assertTrue("Checking validateNullEmpty", this.otherState.validateNullEmpty());
        this.otherState.setName("A");

        assertTrue("Checking validateNullEmpty", this.otherState.validateNullEmpty());
        this.otherState.setIataCode("");
        assertTrue("Checking validateNullEmpty", this.otherState.validateNullEmpty());
        this.otherState.setIataCode("A");

        assertTrue("Checking validateNullEmpty", this.otherState.validateNullEmpty());
        this.otherState.setCountry(new Country());
        assertTrue("Checking validateNullEmpty", this.otherState.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.state.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherState.validateNullEmptyIdentifier());
        this.otherState.setIataCode("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherState.validateNullEmptyIdentifier());
    }
}
