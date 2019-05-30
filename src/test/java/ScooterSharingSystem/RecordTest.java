package ScooterSharingSystem;

import ScooterSharingSystem.models.Record;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * To test record function
 * @author Group 87
 */
public class RecordTest {
    /**
     * To get id
     */
    @Test
    public void testgetid(){
        Date now =new Date();
        Record rec=new Record(123456789,now,now);
        assertEquals(123456789,rec.getId());
    }

    /**
     * To get begin time
     */
    @Test
    public void testgetbegin(){
        Date now =new Date();
        Record rec=new Record(123456789,now,now);
        assertEquals(now,rec.getBegin());
    }

    /**
     * To get end time
     */
    @Test
    public void testgetend(){
        Date now =new Date();
        Record rec=new Record(123456789,now,now);
        assertEquals(now,rec.getEnd());
    }
}
