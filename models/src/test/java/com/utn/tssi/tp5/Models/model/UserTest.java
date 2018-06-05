package com.utn.tssi.tp5.Models.model;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends TestCase {

    User user;
    User otherUser;

    @Before
    public void setUp() {
        this.user = new User(1, "pepe", "pompin");
        this.otherUser = new User("luisito", "contraseña");
    }

    @Test
    public void testToStringOK() {
        String value = this.user.toString();
        assertEquals("Checking toString", value, "{name='pepe', password='pompin'}");
    }

    @Test
    public void testToStringNull() {
        this.user.setName(null);
        String value = this.user.toString();

        assertEquals("Checking toString", value, "{name='null', password='pompin'}");
    }

    @Test
    public void testEqualsNull(){
        boolean value = this.user.equals(null);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject(){
        boolean value = this.user.equals("String");
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes(){

        boolean value = this.user.equals(new User());
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes(){
        boolean value = this.user.equals(this.otherUser);
        assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK(){
        this.otherUser = this.user;

        boolean value = this.user.equals(otherUser);
        assertEquals("Checking equals", value, true);
    }

    @Test
    public void testHashCodeOK() {
        int value = this.user.hashCode();
        assertEquals("Checking hashCode", value, -1371078795);
    }

    @Test
    public void testHashCodeOneNull() {
        this.user.setName(null);
        int value = this.user.hashCode();
        assertEquals("Checking hashCode", value, -379285685);
    }

    @Test
    public void testHashCodeAllNull() {
        this.user = new User();

        int value = this.user.hashCode();
        assertEquals("Checking hashCode", value, 17546899);
    }

    @Test
    public void testValidateNullEmptyOK() {
        boolean value = this.user.validateNullEmpty();
        assertFalse("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeNull() {
        this.user.setName(null);

        boolean value = this.user.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyAttributeEmpty() {
        this.user.setName("");

        boolean value = this.user.validateNullEmpty();
        assertTrue("Checking validateNullEmpty", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        boolean value = this.user.validateNullEmptyIdentifier();
        assertFalse("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        this.user.setName(null);

        boolean value = this.user.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeEmpty() {
        this.user.setName("");

        boolean value = this.user.validateNullEmptyIdentifier();
        assertTrue("Checking validateNullEmptyIdentifier", value);
    }
}
