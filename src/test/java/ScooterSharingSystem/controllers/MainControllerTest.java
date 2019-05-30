package ScooterSharingSystem.controllers;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * To test Main controller function
 * @author Group 87
 */
public class MainControllerTest {
    /**
     * To test is the day is latest week
     */
    @Test
    public void testIsLatestWeek(){
        MainController mc = new MainController();
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        boolean temp = mc.isLatestWeek(now,now);
        assertTrue(temp);
    }

    /**
     * To test is the day is latest week(Error)
     */
    @Test
    public void testIsLatestWeekerror() throws ParseException {
        MainController mc=new MainController();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date before = sdf.parse("2019-01-01 20:11:11");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        boolean temp = mc.isLatestWeek(before,now);
        assertFalse(temp);
    }
}