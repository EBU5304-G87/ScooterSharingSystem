import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Date;

public class Record {
    IntegerProperty id;
    ObjectProperty<Date> begin;
    ObjectProperty<Date> end;

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
    public Record(int id) {
        this.id = new SimpleIntegerProperty(id);
        this.begin = new SimpleObjectProperty<>();
        this.end = new SimpleObjectProperty<>();
    }

    public Record(int id, Date begin, Date end) {
        this.id = new SimpleIntegerProperty(id);
        this.begin = new SimpleObjectProperty<>(begin);
        this.end = new SimpleObjectProperty<>(end);
    }

    public String toString(){
        return "id: " + id +
                " begin: " + begin +
                " end: " + end;
    }
}