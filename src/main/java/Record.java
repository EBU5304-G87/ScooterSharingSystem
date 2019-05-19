import javafx.beans.property.*;

import java.util.Date;

public class Record {
    IntegerProperty id;
    ObjectProperty<Date> begin;
    ObjectProperty<Date> end;
    BooleanProperty isExceeded;

    public void startRecord() {
        begin.set(new Date());
    }

    public void stopRecord() {
        end.set(new Date());
        long time = (getEnd().getTime() - getBegin().getTime()) / 60000;
        if (time > 30)
            isExceeded.set(true);
    }

    boolean getIsExceeded() {
        return isExceeded.get();
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
        this.isExceeded = new SimpleBooleanProperty(false);
    }

    public Record(int id, Date begin, Date end) {
        this.id = new SimpleIntegerProperty(id);
        this.begin = new SimpleObjectProperty<>(begin);
        this.end = new SimpleObjectProperty<>(end);
        this.isExceeded = new SimpleBooleanProperty(false);
    }

    public String toString() {
        return "id: " + id +
                " begin: " + begin +
                " end: " + end;
    }
}