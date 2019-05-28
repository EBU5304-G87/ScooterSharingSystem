import com.fazecast.jSerialComm.SerialPort;
import org.junit.Test;

import static org.junit.Assert.*;

public class WriteListenerTest {
    @Test
    public void test() {
        SerialPort comPort = SerialPort.getCommPort("/dev/tty.SLAB_USBtoUART");
        WriteListener listener = new WriteListener();
        ReceiveListener receive = new ReceiveListener();
        comPort.openPort();
        comPort.addDataListener(receive);
        try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
        // byte[] b = {(byte)'1'};
        // comPort.writeBytes(b, 1);
        comPort.removeDataListener();
        comPort.closePort();
    }
}