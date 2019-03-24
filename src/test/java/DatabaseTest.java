import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {
    @Test
    public void testSingleton() {
        Database db = Database.getInstance();
        assertEquals(db.schoolUsers[0].getEmail(), "igaryhe@gmail.com");
        db.stations.get(0).setLCD("haha233");
        db.saveDatabase();
    }
}