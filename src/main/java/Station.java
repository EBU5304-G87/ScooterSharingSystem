import javafx.application.Platform;
import javafx.beans.property.StringProperty;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is mainly about control of station.
 * @author Group 87
 */
public class Station {
    Slot[] slots;
    StringProperty LCD;
    int unlocked = -1;
    transient Timer timer;
    transient User curUser;

    /**
     * Set LED
     * @param LCD the LED would be set
     */
    public void setLCD(String LCD) {
        this.LCD.set(LCD);
    }

    /**
     * To check is scooter borrowed or not
     * @return is borrowed or not
     */
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

    /**
     * To check is slot is available.
     * @return is available or not
     */
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

    /**
     * This function is about time delay
     * @param slot slot would be operated
     */
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