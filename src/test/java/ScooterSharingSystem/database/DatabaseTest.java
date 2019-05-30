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
    public void testSingleton() {
        Database db = Database.getInstance();
        assertEquals("garyhe@gmail.com", db.schoolUsers[0].getEmail());
        db.stations.get(0).setLCD("haha233");
        db.save();
    }

    /**
     * To test is same day method(Succssful)
     */
    @Test
    public void testIsSameDay(){
        Database db=new Database();
        Date now =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        boolean temp=db.isSameDay(now,now);
        assertEquals(true,temp);
    }

    /**
     * To test is same day method(Error)
     */
    @Test
    public void testIsSameDayerror() throws ParseException {
        Database db=new Database();
        Date now =new Date();
        Date before=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        before=sdf.parse("2019-01-01 20:11:11");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        boolean temp=db.isSameDay(before,now);
        assertEquals(true,temp);
    }

    /**
     * To test is total time exceeded
     */
    @Test
    public void testisTotalTimeExceeded(){
        Database db=new Database();
        assertEquals(false,db.isTotalTimeExceeded(123456789));
    }
}