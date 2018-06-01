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
<<<<<<< HEAD
        this.otherPrice = new Price((float)2.42, "29/06/2018", null, true, cabin2);
=======
<<<<<<< HEAD
        this.otherPrice = new Price(2, (float)2.42, "29/06/2018", null, true, cabin2);
=======
        this.otherPrice = new Price((float)2.42, "29/06/2018", null, true, cabin2);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Test
    public void testToStringOK() {
        String value = this.price.toString();
<<<<<<< HEAD
        assertEquals("Checking toString", value, "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económico', priceKm=1.12}}");
=======
<<<<<<< HEAD
        assertEquals("Checking toString", value, "Price{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin=Cabin{name='Económico', priceKm=1.12}}");
=======
        assertEquals("Checking toString", value, "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin={name='Económico', priceKm=1.12}}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Test
    public void testToStringNull() {
        this.price.setCabin(null);
        String value = this.price.toString();

<<<<<<< HEAD
        assertEquals("Checking toString", value, "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin=null}");
=======
<<<<<<< HEAD
        assertEquals("Checking toString", value, "Price{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin=null}");
=======
        assertEquals("Checking toString", value, "{price=1.12, fromDate='25/06/2018', toDate='null', state_bool=true, cabin=null}");
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
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
<<<<<<< HEAD
        assertEquals("Checking hashCode", value, -686332563);
=======
<<<<<<< HEAD
        assertEquals("Checking hashCode", value, -1534475225);
=======
        assertEquals("Checking hashCode", value, -686332563);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Test
    public void testHashCodeOneNull() {
        this.price.setPrice(0);
        int value = this.price.hashCode();
<<<<<<< HEAD
        assertEquals("Checking hashCode", value, -686333524);
=======
<<<<<<< HEAD
        assertEquals("Checking hashCode", value, -1534505016);
=======
        assertEquals("Checking hashCode", value, -686333524);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
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
<<<<<<< HEAD
        assertEquals("Checking hashCode", value, -686332563);
=======
<<<<<<< HEAD
        assertEquals("Checking hashCode", value, -1534475225);
=======
        assertEquals("Checking hashCode", value, -686332563);
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }
}
