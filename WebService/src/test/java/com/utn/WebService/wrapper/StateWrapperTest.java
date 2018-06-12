package com.utn.WebService.wrapper;

import com.utn.WebService.wrapper.StateWrapper;
import com.utn.tssi.tp5.Models.model.Country;
import com.utn.tssi.tp5.Models.model.State;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class StateWrapperTest extends TestCase {

    StateWrapper state;
    StateWrapper otherState;

    @Before
    public void setUp() {

        Country country = new Country("Argentina", "ARG");
        this.state = new StateWrapper(new State("Buenos Aires", "BA", country));
        this.otherState = new StateWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.state.toString(), "{name='Buenos Aires', iataCode='BA', country={name='Argentina', isoCode='ARG'}}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherState.toString(), "{name='null', iataCode='null', country={name='null', isoCode='null'}}");
    }

    @Test
    public void testEqualsOK(){
        this.otherState = this.state;
        assertTrue("Checking equals", this.state.equals(this.otherState));
    }

    @Test
    public void testEqualsBad(){
        this.otherState = new StateWrapper(new State("La Pampa", "PPPP", null));

        assertFalse("Checking equals", this.state.equals(null));
        assertFalse("Checking equals", this.state.equals("String"));
        assertFalse("Checking equals", this.state.equals(this.otherState));
        this.otherState.setName("Buenos Aires");
        assertFalse("Checking equals", this.state.equals(this.otherState));
        this.otherState.setIataCode("BA");
        assertFalse("Checking equals", this.state.equals(this.otherState));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.state.hashCode(), 1696386087);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherState.hashCode(), 368063);
    }

}
