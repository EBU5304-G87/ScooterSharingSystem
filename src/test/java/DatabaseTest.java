import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void testSingleton() {
        Database db = Database.getInstance();
        assertEquals(db.stations[0].LCD, "This is a message");
    }
}