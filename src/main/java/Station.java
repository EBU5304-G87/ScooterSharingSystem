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


    protected boolean unlockSlot() {
        for (Slot slot:slots) {
            if ((slot.slot).get() && (slot.lock).get()) {
                slot.lock.set(false);
                slot.light.set(true);

                timeDelay(slot);
                slot.slot.set(false);
                return true;
            }
        }
        LCD.set("Scooter hasn't been taken");
        return false;
    }
    protected boolean returnScooter() {
        for (Slot slot:slots) {
            if (!slot.slot.get()) {
                slot.lock.set(false);
                slot.light.set(true);
                timeDelay(slot);
                slot.slot.set(true);
                return true;
            }
        }
        return false;
    }

    private void timeDelay(Slot slot) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                slot.lock.set(true);
                slot.light.set(false);
                System.gc();
            }
        }, 6 * 1000);
    }
}