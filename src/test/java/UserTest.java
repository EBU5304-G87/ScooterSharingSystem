import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void getId() {
        User usr = new User(123456789, "Dan Ho", "igaryhe@gmail.com");
        assertEquals(usr.getId(), 123456789);
    }

    @Test
    public void getName() {
    }

    @Test
    public void getEmail() {
    }

    @Test
    public void isViolation() {
    }

    @Test
    public void setViolation() {
    }
}