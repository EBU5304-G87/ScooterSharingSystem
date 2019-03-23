import java.util.Date;

public class Record {
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

    public void startRecord() {
        this.begin = new Date();
    }

    public void stopRecord() {
        this.end = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }
}