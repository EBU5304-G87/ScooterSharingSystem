import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Station {
    Slot[] slots;
    StringProperty LCD;
    BooleanProperty a;

    public String getLCD() {
        return LCD.get();
    }

    public void setLCD(String LCD) {
        this.LCD.set(LCD);
    }

    public void unlockSlot(int staNum)
    {
        Database databases=new Database();
        int check=0,checkIfTakeaway=0;
        slots=databases.stations.get(staNum).slots;
        LCD=databases.stations.get(staNum).LCD;
        for(int i=0;i< slots.length;i++)
        {
            if((slots[i].slot).getValue()==true&&(slots[i].lock).getValue()==true)
            {
                slots[i].lock.setValue(false);
                long timetwo,timeone=System.currentTimeMillis();
                timetwo=timeone;
                while(true)
                {
                    timeone=System.currentTimeMillis();
                    long d=timeone-timetwo;
                    if(d>600000)
                    {
                        if(checkIfTakeaway==0)
                        {

                            slots[i].lock.setValue(true);
                        }
                        break;
                    }
                    slots[i].slot.setValue(true);
                    if(slots[i].slot.getValue()==true)
                    {
                        checkIfTakeaway = 1;
                        Record rec=recordnow();
                        databases.records.add(rec);
                    }
                }
                databases.stations.get(staNum).slots[i].light.setValue(true);
                delayFun(1500);
                databases.stations.get(staNum).slots[i].light.setValue(false);
                check=1;
                break;
            }
        }
        if(check==0)
        {
            System.out.println("No scooter could be used");
        }
        databases.stations.get(staNum).slots=slots;
        databases.saveDatabase();//保存还有一点问题
    }

    public void delayFun(int di)
    {
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {}
        },di);
    }

    public Record recordnow ()
    {
        int id;
        Date begin, end;
        User newuser=new User();
        id=newuser.getId();
        begin=new Date();
        end=new Date();
        Record rec=new Record(id,begin,end);
        return rec;
    }
    public static void main(String[]args)
    {
        Station a=new Station();
        a.unlockSlot(0);

    }

}