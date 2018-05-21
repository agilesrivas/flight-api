package com.utn.tssi.tp5.ApiRestVuelos.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CabinTest extends TestCase {

    Cabin cabin;

    @Before
    public void setUp() {
        this.cabin = new Cabin();
        this.cabin.setId(1);
        this.cabin.setName("Económico");
        this.cabin.setPriceKm(1.12);
    }

    @Test
    public void testToStringOK() {
        String value = this.cabin.toString();
        assertEquals("Checking toString", value, "Económico - $1.12/km");
    }

    @Test
    public void testToStringNull() {
        this.cabin.setName(null);
        String value = this.cabin.toString();

        assertEquals("Checking toString", value, "null - $1.12/km");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.cabin.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.cabin.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        Cabin otherCabin = new Cabin(0, null, 0);
        boolean value = this.cabin.equals(otherCabin);

        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        Cabin otherCabin = new Cabin(2, "VIP", 1.50);
        boolean value = this.cabin.equals(otherCabin);

        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        Cabin otherCabin = new Cabin(1, "Económico", 1.12);
        boolean value = this.cabin.equals(otherCabin);

        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.cabin.hashCode();
        assertEquals("Checking hashCode", value, -1733002252);
    }

    @Test
    public void testHashCodeOneNull() {
        this.cabin.setName(null);
        int value = this.cabin.hashCode();
        assertEquals("Checking hashCode", value, 477618);
    }

    @Test
    public void testHashCodeAllNull() {
        this.cabin.setId(0);
        this.cabin.setName(null);
        this.cabin.setPriceKm(0);

        int value = this.cabin.hashCode();
        assertEquals("Checking hashCode", value, 476656);
    }
}
