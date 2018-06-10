package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class CabinTest extends TestCase {

    Cabin cabin;
    Cabin otherCabin;

    @Before
    public void setUp() {
        this.cabin = new Cabin(1, "Económico");
        this.otherCabin = new Cabin();
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
        this.otherCabin = new Cabin("VIP");

        assertFalse("Checking equals", this.cabin.equals(null));
        assertFalse("Checking equals", this.cabin.equals("String"));
        assertFalse("Checking equals", this.cabin.equals(this.otherCabin));
        this.otherCabin.setId(1);
        assertFalse("Checking equals", this.cabin.equals(this.otherCabin));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.cabin.hashCode(), -610092627);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherCabin.hashCode(), 15376);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.cabin.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherCabin.validateNullEmpty());
        this.otherCabin.setName("");
        assertTrue("Checking validateNullEmpty", this.otherCabin.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.cabin.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherCabin.validateNullEmptyIdentifier());
        this.otherCabin.setName("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherCabin.validateNullEmptyIdentifier());
    }
}
