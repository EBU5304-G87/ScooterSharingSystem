package ScooterSharingSystem.models;

import ScooterSharingSystem.database.Database;
import ScooterSharingSystem.listeners.ReceiveListener;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is mainly about control of station.
 * @author Group 87
 */
public class Station {
    public Slot[] slots;
    public StringProperty LCD;
    public int unlocked = -1;
    public transient Timer timer;
    public transient User curUser;
    private transient SerialPort comPort = SerialPort.getCommPort("/dev/tty.SLAB_USBtoUART");
    private transient byte[] lights = {0b01111111, (byte) 0b10111111, (byte) 0b11011111,
            (byte) 0b11101111, (byte) 0b11110111, (byte) 0b11111011,
            (byte) 0b11111101, (byte) 0b11111110};

    Station() {
        ReceiveListener receive = new ReceiveListener();
        comPort.openPort();
        comPort.addDataListener(receive);
    }
    /**
     * Set LED
     * @param LCD the LED would be set
     */
    public void setLCD(String LCD) {
        this.LCD.set(LCD);
        String send = LCD + '.';
        comPort.writeBytes(send.getBytes(), send.length());
    }

    /**
     * To check is scooter borrowed or not
     * @return is borrowed or not
     */
    public boolean borrowScooter() {
        int i = 0;
        for (Slot slot : slots) {
            if ((slot.slot).get() && (slot.lock).get()) {
                // byte[] send = {lights[i], (byte)'.'};
                // comPort.writeBytes(send, 2);
                setLCD("Slot " + (i + 1) + " unlocked");
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
    public boolean returnScooter() {
        int i = 0;
        for (Slot slot:slots) {
            if (!slot.slot.get() && (slot.lock).get()) {
                // byte[] send = {lights[i], (byte)'.'};
                // comPort.writeBytes(send, 2);
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
        }, 6000);
    }
}