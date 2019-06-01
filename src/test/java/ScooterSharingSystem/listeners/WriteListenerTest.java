package ScooterSharingSystem.listeners;

import com.fazecast.jSerialComm.SerialPort;
import org.junit.Test;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import static org.junit.Assert.*;

public class WriteListenerTest {
    @Test
    public void test() {
        SerialPort comPort = SerialPort.getCommPort("/dev/tty.SLAB_USBtoUART");
        ReceiveListener receive = new ReceiveListener();
        comPort.openPort();
        comPort.addDataListener(receive);
        // try { Thread.sleep(5000); } catch (Exception e) { e.printStackTrace(); }
        comPort.removeDataListener();
        comPort.closePort();
    }
}