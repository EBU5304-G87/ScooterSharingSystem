package ScooterSharingSystem.listeners;

import ScooterSharingSystem.database.Database;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.util.Arrays;

/**
 * This class is about receive listener.
 * @author Group 87
 */
public final class ReceiveListener implements SerialPortMessageListener {
    private Database db;

    public ReceiveListener() {
        db = Database.getInstance();
    }

    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte)'.' }; }

    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] delimitedMessage = event.getReceivedData();
        db = Database.getInstance();
        if (delimitedMessage.length == 10) {
            byte[] b = Arrays.copyOf(delimitedMessage, delimitedMessage.length - 1);
            String s = new String(b);
            int i = Integer.parseInt(s);
            db.unlock(i);
        } else if (delimitedMessage.length == 2) {
            byte[] b = Arrays.copyOf(delimitedMessage, delimitedMessage.length - 1);
            String s = new String(b);
            int i = Integer.parseInt(s);
            if (i == 1) db.take();
        }
    }
}