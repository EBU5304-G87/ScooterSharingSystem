package ScooterSharingSystem.models;

import javafx.beans.property.*;
import java.util.Date;

/**
 * This class is mainly about record scooters' behaviors.
 * @author Group 87
 */

public class Record {
    public IntegerProperty id;
    public ObjectProperty<Date> begin;
    public ObjectProperty<Date> end;
    BooleanProperty isExceeded;

    /**
     * set date to start time of  borrow.
     */
    public void startRecord() {
        begin.set(new Date());
    }

    /**
     * Get and set time of end time of borrow
     */
    public void stopRecord() {
        end.set(new Date());
        long time = (getEnd().getTime() - getBegin().getTime()) / 60000;
        if (time > 30)
            isExceeded.set(true);
    }

    /**
     * Get is it exceeded
     * @return is exceeded or not.
     */
    public boolean getIsExceeded() {
        return isExceeded.get();
    }

    /**
     * Get id
     * @return the id
     */
    public int getId() {
        return id.get();
    }

    /**
     * Get information of begin
     * @return information of begin
     */
    public Date getBegin() {
        return begin.get();
    }

    /**
     * Get information of end
     * @return information of end
     */
    public Date getEnd() {
        return end.get();
    }

    /**
     * Constructor function of record
     * @param id users' id
     */
    public Record(int id) {
        this.id = new SimpleIntegerProperty(id);
        this.begin = new SimpleObjectProperty<>();
        this.end = new SimpleObjectProperty<>();
        this.isExceeded = new SimpleBooleanProperty(false);
    }

    /**
     * Constructor function of record
     * @param id users' id
     * @param begin begin time
     * @param end end time
     */
    public Record(int id, Date begin, Date end) {
        this.id = new SimpleIntegerProperty(id);
        this.begin = new SimpleObjectProperty<>(begin);
        this.end = new SimpleObjectProperty<>(end);
        this.isExceeded = new SimpleBooleanProperty(false);
    }

    /**
     * Output data in string
     * @return data of id, begin time and end time.
     */
    public String toString() {
        return "id: " + id +
                " begin: " + begin +
                " end: " + end;
    }
}