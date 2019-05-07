import org.junit.Test;
import java.util.Date;

public class RecordBehaviorTest {
    @Test
    public void testRecordPick(){
        Database db = Database.getInstance();
        RecordBehavior rb = new RecordBehavior();

        rb.recordPickUp(123456789);
        System.out.println(db.records.get(db.records.size()-1).getId() + "+" + db.records.get(db.records.size()-1).getBegin());
        db.saveDatabase();
    }

    @Test
    public void testRecordReturn(){
        Database db = Database.getInstance();
        RecordBehavior rb = new RecordBehavior();

        long duration = rb.recordReturn(123456789);
        System.out.println(db.records.get(db.records.size()-1).getId() + "+"
                + db.records.get(db.records.size()-1).getBegin() + "+"
                + db.records.get(db.records.size()-1).getEnd() + "+" + duration);
        System.out.println(rb.getTotalUseTime(123456789, new Date()));
        db.saveDatabase();
    }
}
