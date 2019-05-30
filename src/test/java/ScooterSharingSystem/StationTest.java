package ScooterSharingSystem;

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
    public void testBorrowScooter(){
        Database db=Database.getInstance();
        Station st=db.stations.get(1);
        assertEquals(true,st.borrowScooter());
    }

    /**
     * To test returnScooter method
     */
    @Test
    public void testReturnScooter(){
        Database db=Database.getInstance();
        Station st=db.stations.get(0);
        assertEquals(true,st.returnScooter());
    }
}