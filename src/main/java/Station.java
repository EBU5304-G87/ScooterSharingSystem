import javafx.application.Platform;
import javafx.beans.property.StringProperty;

import java.util.Timer;
import java.util.TimerTask;

public class Station {
    Slot[] slots;
    StringProperty LCD;
    int unlocked = -1;
    transient Timer timer;
    transient User curUser;

    public void setLCD(String LCD) {
        this.LCD.set(LCD);
    }

    boolean borrowScooter() {
        int i = 0;
        for (Slot slot : slots) {
            if ((slot.slot).get() && (slot.lock).get()) {
                LCD.set("Slot " + (i + 1) + " unlocked");
                slot.lock.set(false);
                slot.light.set(true);
                timeDelay(slot);
                unlocked = i;
                return true;
            }
            i++;
        }
        setLCD("No scooter available");
        return false;
    }

    boolean returnScooter() {
        int i = 0;
        for (Slot slot:slots) {
            if (!slot.slot.get() && (slot.lock).get()) {
                setLCD("Slot " + (i + 1) + " unlocked");
                slot.lock.set(false);
                slot.light.set(true);
                timeDelay(slot);
                unlocked = i;
                return true;
            }
            i++;
        }
        setLCD("No slot available");
        return false;
    }

    private void timeDelay(Slot slot) {
        Database db = Database.getInstance();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                slot.lock.set(true);
                slot.light.set(false);
                Platform.runLater(() -> setLCD("Locked"));
                unlocked = -1;
                db.save();
                timer.cancel();
                timer.purge();
            }
        }, 6 * 1000);
    }
}