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
        this.otherUser = new User();
    }

    @Test
    public void testToStringOK() {
        assertEquals("Checking toString", this.user.toString(), "{name='pepe', password='pompin'}");
    }

    @Test
    public void testToStringBad() {
        assertEquals("Checking toString", this.otherUser.toString(), "{name='null', password='null'}");
    }

    @Test
    public void testEqualsOK(){
        this.otherUser = this.user;
        assertTrue("Checking equals", this.user.equals(otherUser));
    }

    @Test
    public void testEqualsBad(){
        this.otherUser = new User("Brasil", "BR");

        assertFalse("Checking equals", this.user.equals(null));
        assertFalse("Checking equals", this.user.equals("String"));
        assertFalse("Checking equals", this.user.equals(this.otherUser));
        this.otherUser.setId(1);
        assertFalse("Checking equals", this.user.equals(this.otherUser));
        this.otherUser.setName("pepe");
        assertFalse("Checking equals", this.user.equals(this.otherUser));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.user.hashCode(), -875512341);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherUser.hashCode(), 566029);
    }

    @Test
    public void testValidateNullEmptyOK() {
        assertFalse("Checking validateNullEmpty", this.user.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyBad() {
        assertTrue("Checking validateNullEmpty", this.otherUser.validateNullEmpty());
        this.otherUser.setName("");
        assertTrue("Checking validateNullEmpty", this.otherUser.validateNullEmpty());
        this.otherUser.setName("A");

        assertTrue("Checking validateNullEmpty", this.otherUser.validateNullEmpty());
        this.otherUser.setPassword("");
        assertTrue("Checking validateNullEmpty", this.otherUser.validateNullEmpty());
    }

    @Test
    public void testValidateNullEmptyIdentifierOK() {
        assertFalse("Checking validateNullEmptyIdentifier", this.user.validateNullEmptyIdentifier());
    }

    @Test
    public void testValidateNullEmptyIdentifierAttributeNull() {
        assertTrue("Checking validateNullEmptyIdentifier", this.otherUser.validateNullEmptyIdentifier());
        this.otherUser.setName("");
        assertTrue("Checking validateNullEmptyIdentifier", this.otherUser.validateNullEmptyIdentifier());
    }
}
