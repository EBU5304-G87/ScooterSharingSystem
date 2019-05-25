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
    public void getId() {
        User usr = new User(123456789, "Dan Ho", "igaryhe@gmail.com");
        assertEquals(usr.getId(), 123456789);
    }

    /**
     * Test get name function
     */
    @Test
    public void getName() {
    }

    /**
     * Test get email function
     */
    @Test
    public void getEmail() {
    }

    /**
     * Test is violation function
     */
    @Test
    public void isViolation() {
    }

    /**
     * Test set violation function
     */
    @Test
    public void setViolation() {
    }
}