package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Cabin;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CabinWrapperTest extends TestCase {

    CabinWrapper cabin;
    CabinWrapper otherCabin;

    @Before
    public void setUp() {
        this.cabin = new CabinWrapper(new Cabin(1, "Económico"));
        this.otherCabin = new CabinWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.cabin.toString(), "{name='Económico'}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherCabin.toString(), "{name='null'}");
    }

    @Test
    public void testEqualsOK(){
        this.otherCabin = this.cabin;
        assertTrue("Checking equals", this.cabin.equals(otherCabin));
    }

    @Test
    public void testEqualsBad(){
        this.otherCabin = new CabinWrapper(new Cabin("VIP"));

        assertFalse("Checking equals", this.cabin.equals(null));
        assertFalse("Checking equals", this.cabin.equals("String"));
        assertFalse("Checking equals", this.cabin.equals(this.otherCabin));
        this.cabin.setName("Económico");
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.cabin.hashCode(), -610107538);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherCabin.hashCode(), 496);
    }

}
