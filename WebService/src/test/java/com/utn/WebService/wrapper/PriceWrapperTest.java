package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Cabin;
import com.utn.tssi.tp5.Models.model.Price;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PriceWrapperTest extends TestCase {

    PriceWrapper price;
    PriceWrapper otherPrice;

    @Before
    public void setUp() {
        Cabin cabin = new Cabin(1, "Económico");

        this.price = new PriceWrapper(new Price(1, (float)1.12, "25/06/2018", "30/06/2018",false, cabin));
        this.otherPrice = new PriceWrapper(null);
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.price.toString(), "{price=1.12, fromDate='25/06/2018', toDate='30/06/2018', cabin={name='Económico'}}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherPrice.toString(), "{price=0.0, fromDate='null', toDate='null', cabin={name='null'}}");
    }

    @Test
    public void testEqualsOK(){
        this.otherPrice = this.price;
        assertTrue("Checking equals", this.price.equals(otherPrice));
    }

    @Test
    public void testEqualsBad(){
        this.otherPrice = new PriceWrapper(new Price((float)2.42, "29/06/2018", "89/88/8888",false, new Cabin()));

        assertFalse("Checking equals", this.price.equals(null));
        assertFalse("Checking equals", this.price.equals("String"));
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setPrice((float)1.12);
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setFrom_Date("25/06/2018");
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
        this.otherPrice.setTo_Date(this.price.getTo_Date());
        assertFalse("Checking equals", this.price.equals(this.otherPrice));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.price.hashCode(), 669247286);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherPrice.hashCode(), 14329471);
    }

}
