package ScooterSharingSystem.models;

import ScooterSharingSystem.database.Database;
import ScooterSharingSystem.helpers.OSCheck;
import ScooterSharingSystem.listeners.ReceiveListener;
import com.fazecast.jSerialComm.SerialPort;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * This class is mainly about control of station.
 * @author Group 87
 */
public class Station {
    public Slot[] slots;
    public StringProperty LCD;
    public int unlocked = -1;
    public transient User curUser;
    public transient SerialPort comPort;
    public transient ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    public transient ScheduledFuture<?> future;

    Station() {
        if (OSCheck.isMac()){
            comPort = SerialPort.getCommPort("/dev/tty.SLAB_USBtoUART");
        } else if (OSCheck.isWindows()) {
            comPort = SerialPort.getCommPort("COM3");
        } else if (OSCheck.isUnix()) {
            comPort = SerialPort.getCommPort("/dev/ttyS2");
        }
        ReceiveListener receive = new ReceiveListener();
        comPort.openPort();
        comPort.addDataListener(receive);
    }

    /**
     * Set LED
     *
     * @param LCD the LED would be set
     */
    public void setLCD(String LCD) {
        Platform.runLater(() -> this.LCD.set(LCD));
        String send = LCD + '*';
        comPort.writeBytes(send.getBytes(), send.length());
    }

    public void prompt() {
        String LCD = "Please input your ID";
        String s = LCD + '*';
        Platform.runLater(() -> this.LCD.set(s));
        comPort.writeBytes(s.getBytes(), s.length());
    }

    public void setFailedLCD(String s) {
        setLCD(s);
        byte[] failed = {0x00, '*'};
        comPort.writeBytes(failed, 2);
    }

    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void unlock(int i) {
        byte[] lights = {0x7F, (byte) 0xBF, (byte) 0xDF, (byte) 0xEF,
                (byte) 0xF7, (byte) 0xFB, (byte) 0xFD, (byte) 0xFE};
        Slot slot = slots[i];

        setLCD("Slot " + (i + 1) + " unlocked");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comPort.writeBytes(new byte[] {lights[i], '*'}, 2);
        slot.lock.set(false);
        slot.light.set(true);
        Database db = Database.getInstance();
        future = executor.schedule(() -> {
            slot.lock.set(true);
            slot.light.set(false);
            setLCD("Locked");
            unlocked = -1;
            db.save();
        }, 62, TimeUnit.SECONDS);
        unlocked = i;
    }

    /**
     * To check is scooter borrowed or not
     *
     * @return is borrowed or not
     */
    public boolean borrowScooter() {
        int i = 0;
        for (Slot slot : slots) {
            if ((slot.slot).get() && (slot.lock).get()) {
                unlock(i);
                return true;
            }
            i++;
        }
        setFailedLCD("No scooter available");
        return false;
    }

    /**
     * To check is slot is available.
     *
     * @return is available or not
     */
    public boolean returnScooter() {
        int i = 0;
        for (Slot slot : slots) {
            if (!slot.slot.get() && (slot.lock).get()) {
                unlock(i);
                return true;
            }
            i++;
        }
        setFailedLCD("No slot available");
        return false;
    }
}