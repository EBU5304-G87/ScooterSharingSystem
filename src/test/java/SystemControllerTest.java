import org.junit.Test;

import static org.junit.Assert.*;

public class SystemControllerTest {

    @Test
    public void register() {
        SystemController sc = new SystemController();
        sc.register(123456789);
        assertEquals(sc.verifyId(123456789), true);
    }
}