import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;
import java.util.Arrays;

/**
 * This class is about receive listener.
 * @author Group 87
 */
public final class ReceiveListener implements SerialPortMessageListener {

    /**
     * Get message delimiter
     * @return byte
     */
    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte)'.' }; }

    /**
     * Delimiter indicates end of message.
     * @return true
     */
    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }

    /**
     * Get listening events
     * @return the serial port received.
     */
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    /**
     * Serial event
     * @param event the event it has
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] delimitedMessage = event.getReceivedData();
        if (delimitedMessage.length == 10) {

        } else if (delimitedMessage.length == 2) {
        }
        for (byte b : delimitedMessage) System.out.print((char) b);
        System.out.println();
    }
}
