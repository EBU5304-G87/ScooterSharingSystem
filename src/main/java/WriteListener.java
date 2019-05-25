import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

/**
 * This is control about interface write listener.
 * @author Group 87
 */
public class WriteListener implements SerialPortDataListener {
    /**
     * Get events about listening
     * @return events about listening
     */
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_WRITTEN; }

    /**
     * Output information about transmitted
     * @param event the serial event
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_WRITTEN)
            System.out.println("All bytes were successfully transmitted!");
    }
}