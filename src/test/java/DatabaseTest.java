import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is test about database control.
 * @author Group 87
 */
public class DatabaseTest {
    /**
     * To test add information.
     */
    @Test
    public void testSingleton() {
        Database db = Database.getInstance();
        assertEquals("garyhe@gmail.com", db.schoolUsers[0].getEmail());
        db.stations.get(0).setLCD("haha233");
        db.save();
    }
}