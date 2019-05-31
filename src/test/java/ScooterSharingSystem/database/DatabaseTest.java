package ScooterSharingSystem.database;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    public void singletonTest() {
        Database db = Database.getInstance();
        assertEquals("igaryhe@gmail.com", db.schoolUsers[0].getEmail());
    }

    /**
     * To test is same day method(Successful)
     */
    @Test
    public void isSameDayTest(){
        Database db=Database.getInstance();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        boolean temp = db.isSameDay(now,now);
        assertTrue(temp);
    }

    /**
     * To test is same day method(Error)
     */
    @Test
    public void isSameDayErrorTest() throws ParseException {
        Database db = Database.getInstance();
        Date now = new Date();
        Date before;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        before = sdf.parse("2019-01-01 20:11:11");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        boolean temp=db.isSameDay(before,now);
        assertFalse(temp);
    }

    /**
     * To test is total time exceeded
     */
    @Test
    public void isTotalTimeExceededTest(){
        Database db = new Database();
        assertFalse(db.isTotalTimeExceeded(123456789));
    }
}