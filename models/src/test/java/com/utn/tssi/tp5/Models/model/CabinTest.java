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
        this.otherCabin = new Cabin("VIP");
    }

    @Test
    public void testToStringOK() {
        String value = this.cabin.toString();
        assertEquals("Checking toString", value, "{name='Económico'}");
    }

    @Test
    public void testToStringNull() {
        this.cabin.setName(null);
        String value = this.cabin.toString();

        assertEquals("Checking toString", value, "{name='null'}");
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

        boolean value = this.cabin.equals(new Cabin());
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.cabin.equals(this.otherCabin);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherCabin = this.cabin;

        boolean value = this.cabin.equals(otherCabin);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.cabin.hashCode();
        assertEquals("Checking hashCode", value, -610092627);
    }

    @Test
    public void testHashCodeOneNull() {
        this.cabin.setName(null);
        int value = this.cabin.hashCode();
        assertEquals("Checking hashCode", value, 15407);
    }

    @Test
    public void testHashCodeAllNull() {
        this.cabin = new Cabin();

        int value = this.cabin.hashCode();
        assertEquals("Checking hashCode", value, 15376);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.cabin.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.cabin.setName(null);

        boolean value = this.cabin.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.cabin.setName("");

        boolean value = this.cabin.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        boolean value = this.cabin.validateNullEmptyIdentifier();
        assertFalse("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        this.cabin.setName(null);

        boolean value = this.cabin.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeEmpty() {
        this.cabin.setName("");

        boolean value = this.cabin.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }
}
