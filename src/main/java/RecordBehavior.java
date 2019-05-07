import java.util.Calendar;
import java.util.Date;

public class RecordBehavior {
    Database db = Database.getInstance();

    /**
     * Record user ID and start time when user pick up a scooter.
     * @param id user ID
     */
    public void recordPickUp(int id){
        Record rc = new Record(id);
        rc.startRecord();
        db.records.add(rc);
    }

    /**
     * Record end time when user return a scooter and set the user's Violation
     * @param id user ID
     * @return single use time in minute
     */
    public long recordReturn(int id){
        Record rc = null;
        Date latest = null;

        for(int i=0; i<db.records.size(); i++){
            if(db.records.get(i).getId() == id){
                if(latest == null || latest.before(db.records.get(i).getBegin())) {
                    rc = db.records.get(i);
                    latest = rc.getBegin();
                }
            }
        }
        if(rc != null)   rc.stopRecord();
        for(int i=0; i<db.records.size(); i++){
            if(db.records.get(i).getId()== id)
                if( (rc.getEnd().getTime() - rc.getBegin().getTime()) / 60000>30)
                    db.getUserById(id).setViolation(true);
        }
        return (rc.getEnd().getTime()
                - rc.getBegin().getTime())
                / 60000;
    }

    /**
     * get total use time in a particular day and set the user's Violation
     * @param id user ID
     * @param aDate the day used to search
     * @return total use time in minute
     */
    public long getTotalUseTime(int id, Date aDate){
        long totalTime = 0;

        for(int i=0; i<db.records.size(); i++){
            if(db.records.get(i).getId() == id && isSameDay(db.records.get(i).getBegin(), aDate)){
                totalTime += (db.records.get(i).getEnd().getTime()
                           - db.records.get(i).getBegin().getTime())
                           / 60000;
            }
        }
        for(int i=0; i<db.records.size(); i++){
            if(db.records.get(i).getId()== id)
                if(totalTime>120)
                    db.getUserById(id).setViolation(true);
        }
        return totalTime;
    }

    /**
     * judge if two time is in the same day
     * @param date1 the first time to be judged
     * @param date2 the second time to be judged
     * @return true if two time is in the same day
     */
    private boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))    return true;
        else return false;
    }
}
