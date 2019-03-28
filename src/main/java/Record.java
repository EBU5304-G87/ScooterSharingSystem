import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class Record {
    /*
    private int id;
    private Date begin;
    private Date end;

    public Record(int id) {
        this.id = id;
    }

    public Record(int id, Date begin, Date end) {
        this.id = id;
        this.begin = begin;
        this.end = end;
    }
    */
    public void startRecord() {
        begin.set(new Date());
    }

    public void stopRecord() {
        end.set(new Date());
    }

    public int getId() {
        return id.get();
    }

    public Date getBegin() {
        return begin.get();
    }

    public Date getEnd() {
        return end.get();
    }
    IntegerProperty id;
    ObjectProperty<Date> begin;
    ObjectProperty<Date> end;
    public Record(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    public Record(int id, Date begin, Date end) {
        this.id = new SimpleIntegerProperty(id);
        this.begin = new SimpleObjectProperty<Date>(begin);
        this.end = new SimpleObjectProperty<Date>(end);
    }
}