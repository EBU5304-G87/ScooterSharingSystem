import javafx.beans.property.StringProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Station {
    Slot[] slots;
    StringProperty LCD;

    public String getLCD() {
        return LCD.get();
    }

    public void setLCD(String LCD) {
        this.LCD.set(LCD);
    }


    public int unlockSlot(int staNum)
    {
        Database databases=Database.getInstance();
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
                    if(slots[i].slot.getValue()==false)
                        checkIfTakeaway = 1;
                }
                databases.stations.get(staNum).slots[i].light.setValue(true);
                delayFun(1500);
                databases.stations.get(staNum).slots[i].light.setValue(false);
                delayFun(1500);
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
        databases.saveDatabase();
        return checkIfTakeaway;
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


    public void main(String[] args) {
        unlockSlot(2);
    }

}