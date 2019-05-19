import org.junit.Test;
import java.util.Date;

/**
 *
 */
public class RecordBehaviorTest {
    @Test
    public void testRecordPick(){
        int id = 123456789;
        Database db = Database.getInstance();
        RecordBehavior rb = new RecordBehavior();

        rb.recordPickUp(id);
        System.out.println(db.records.get(db.records.size()-1));
        for(int i=0; i<db.users.size(); i++) {
            if(db.users.get(i).getId() == id)
                System.out.println(db.users.get(i));
        }

        db.save();
    }

    @Test
    public void testRecordReturn(){
        int id = 123456789;
        Database db = Database.getInstance();
        RecordBehavior rb = new RecordBehavior();

        long duration = rb.recordReturn(id);
        System.out.println(db.records.get(db.records.size()-1));
        System.out.println("single: " + duration);
        System.out.println("total: " + rb.getTotalUseTime(id, new Date()));
        for(int i=0; i<db.users.size(); i++) {
            if(db.users.get(i).getId() == id)
                System.out.println(db.users.get(i));
        }
        db.save();
    }
}
