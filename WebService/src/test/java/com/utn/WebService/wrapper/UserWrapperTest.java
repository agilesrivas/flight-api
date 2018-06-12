package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class UserWrapperTest extends TestCase {

    UserWrapper user;
    UserWrapper otherUser;

    @Before
    public void setUp() {
        this.user = new UserWrapper(new User(1, "pepe", "pompin"));
        this.otherUser = new UserWrapper(null);
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
        this.otherUser = new UserWrapper(new User("Brasil", "BR"));

        assertFalse("Checking equals", this.user.equals(null));
        assertFalse("Checking equals", this.user.equals("String"));
        assertFalse("Checking equals", this.user.equals(this.otherUser));
        this.otherUser.setName("pepe");
        assertFalse("Checking equals", this.user.equals(this.otherUser));
    }

    @Test
    public void testHashCodeOK() {
        assertEquals("Checking hashCode", this.user.hashCode(), -876061072);
    }

    @Test
    public void testHashCodeBad() {
        assertEquals("Checking hashCode", this.otherUser.hashCode(), 18259);
    }

}
