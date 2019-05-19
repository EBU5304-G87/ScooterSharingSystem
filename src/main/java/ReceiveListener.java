import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListener;

import java.util.Arrays;

public final class ReceiveListener implements SerialPortMessageListener {

    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte)0x2E }; }

    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }

    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] delimitedMessage = event.getReceivedData();
        System.out.println("Received the following delimited message: " + Arrays.toString(delimitedMessage));
    }
}
