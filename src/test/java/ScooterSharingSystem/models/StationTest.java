package ScooterSharingSystem.models;

import ScooterSharingSystem.database.Database;
import ScooterSharingSystem.models.Station;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To test function about station
 * @author Group 87
 */
public class StationTest {
    /**
     * To test borrowScooter method
     */
    @Test
    public void borrowCheckTest(){
        Database db=Database.getInstance();
        Station st=db.station;
        assertTrue(st.borrowScooter());
    }

    /**
     * To test returnScooter method
     */
    @Test
    public void returnCheckTest(){
        Database db=Database.getInstance();
        Station st=db.station;
        assertFalse(st.returnScooter());
    }
}