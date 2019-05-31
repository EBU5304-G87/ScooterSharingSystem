package ScooterSharingSystem.models;

import ScooterSharingSystem.models.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test function about user
 * @author Group 87
 */
public class UserTest {

    /**
     * Test get id function
     */
    @Test
    public void getIdTest() {
        User usr = new User(123456789, "Dan Ho", "igaryhe@gmail.com");
        assertEquals(usr.getId(), 123456789);
    }

    /**
     * Test get name function
     */
    @Test
    public void getNameTest() {
        User usr = new User(123456789, "Dan Ho", "igaryhe@gmail.com");
        assertEquals(usr.getName(), "Dan Ho");
    }

    /**
     * Test get email function
     */
    @Test
    public void getEmailTest() {
        User usr = new User(123456789, "Dan Ho", "igaryhe@gmail.com");
        assertEquals(usr.getEmail(), "igaryhe@gmail.com");
    }

}