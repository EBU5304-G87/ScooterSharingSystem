import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.util.Arrays;

public final class ReceiveListener implements SerialPortMessageListener {

    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte)'.' }; }

    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

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
