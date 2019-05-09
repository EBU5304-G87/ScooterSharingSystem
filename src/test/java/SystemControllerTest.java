import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class SystemControllerTest {

    @Test
    public void register() {
        SystemController sc = new SystemController();
        assertEquals(sc.register(161188760, "Zhixian He", "h@hhnet"), false);
        EmailValidator ev = EmailValidator.getInstance();
        assertEquals(ev.isValid("h@hhhnet"), false);
    }
}