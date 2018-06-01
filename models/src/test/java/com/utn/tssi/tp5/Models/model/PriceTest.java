package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PriceTest extends TestCase {

    Price price;
    Price otherPrice;

    @Before
    public void setUp() {
        Cabin cabin = new Cabin(1, "Económico", 1.12);
        Cabin cabin2 = new Cabin(2, "VIP", 2.42);

        this.price = new Price(1, (float)1.12, "25/06/2018", null, true, cabin);
        this.otherPrice = new Price((float)2.42, "29/06/2018", null, true, cabin2);
    }

    @Test
    public void testToStringOK() {
        String value = this.price.toString();
        assertEquals("Checking toString", value, "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económico', priceKm=1.12}}");
    }

    @Test
    public void testToStringNull() {
        this.price.setCabin(null);
        String value = this.price.toString();

        assertEquals("Checking toString", value, "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin=null}");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.price.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.price.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){
        this.otherPrice.setId(0);
        this.otherPrice.setPrice(0);
        this.otherPrice.setCabin(null);
        this.otherPrice.setFromDate(null);
        this.otherPrice.setToDate(null);
        this.otherPrice.setState_bool(false);

        boolean value = this.price.equals(otherPrice);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.price.equals(this.otherPrice);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherPrice = this.price;

        boolean value = this.price.equals(otherPrice);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.price.hashCode();
        assertEquals("Checking hashCode", value, 762321123);
    }

    @Test
    public void testHashCodeOneNull() {
        this.price.setPrice(0);
        int value = this.price.hashCode();
        assertEquals("Checking hashCode", value, 762291332);
    }

    @Test
    public void testHashCodeAllNull() {
        this.otherPrice.setId(0);
        this.otherPrice.setPrice(0);
        this.otherPrice.setCabin(null);
        this.otherPrice.setFromDate(null);
        this.otherPrice.setToDate(null);
        this.otherPrice.setState_bool(false);

        int value = this.price.hashCode();
        assertEquals("Checking hashCode", value, 762321123);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.price.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.price.setCabin(null);

        boolean value = this.price.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.price.setPrice(-1);

        boolean value = this.price.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }
}
