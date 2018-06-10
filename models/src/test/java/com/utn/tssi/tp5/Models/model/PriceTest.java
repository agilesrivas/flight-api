package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PriceTest extends TestCase {

    Price price;
    Price otherPrice;

    @Before
    public void setUp() {
        Cabin cabin = new Cabin(1, "Económico");

        this.price = new Price(1, (float)1.12, "25/06/2018", null, true, cabin);
        this.otherPrice = new Price();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.price.toString(), "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económico'}}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherPrice.toString(), "{price=0.0, fromDate='null', toDate='null', state_bool=false, cabin=null}");
    }

    @Test
    public void testEqualsOK(){
        this.otherPrice = this.price;
        assertTrue("Checking equals", this.price.equals(otherPrice));
    }

    @Test
    public void testEqualsBad(){
        this.otherPrice = new Price((float)2.42, "29/06/2018", "89/88/8888", false, new Cabin());

        assertFalse("Checking equals", this.price.equals(null));
        assertFalse("Checking equals", this.price.equals("String"));
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setId(1);
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setPrice((float)1.12);
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setFrom_Date("25/06/2018");
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setTo_Date(null);
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setState_bool(true);
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.price.hashCode(), 1841679452);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherPrice.hashCode(), 429437265);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.price.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherPrice.validateNullEmpty());
        this.otherPrice.setPrice(-1);
        assertTrue("Checking validateNullEmpty", this.otherPrice.validateNullEmpty());
        this.otherPrice.setPrice(0);

        assertTrue("Checking validateNullEmpty", this.otherPrice.validateNullEmpty());
        this.otherPrice.setCabin(new Cabin());
        assertTrue("Checking validateNullEmpty", this.otherPrice.validateNullEmpty());
        this.otherPrice.setCabin(this.price.getCabin());

        assertTrue("Checking validateNullEmpty", this.otherPrice.validateNullEmpty());
        this.otherPrice.setFrom_Date("");
        assertTrue("Checking validateNullEmpty", this.otherPrice.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.price.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherPrice.validateNullEmptyIdentifier());
        this.otherPrice.setCabin(new Cabin());
        assertTrue("Checking validateNullEmptyIdentifier", this.otherPrice.validateNullEmptyIdentifier());
    }
}
