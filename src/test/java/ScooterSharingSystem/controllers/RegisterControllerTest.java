package ScooterSharingSystem.controllers;;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * To test Register controller function
 * @author Group 87
 */
public class RegisterControllerTest {

    /**
     * To test register is successful or not(Successful)
     */
    @Test
    public void testRegister(){
        RegisterController rc=new RegisterController();
        boolean temp=rc.register(123456789,"Dan Ho","igaryhe@gmail.com");
        assertEquals(true,temp);
    }

    /**
     * To test register is successful or not(Error format in email)
     */
    @Test
    public void testRegisterEmail(){
        RegisterController rc=new RegisterController();
        boolean temp=rc.register(123456789,"Dan Ho","not a email");
        assertEquals(true,temp);
    }

    /**
     * To test register is successful or not(Error format in id)
     */
    @Test
    public void testRegisterid(){
        RegisterController rc=new RegisterController();
        boolean temp=rc.register(1234,"Dan Ho","igaryhe@gmail.com");
        assertEquals(true,temp);
    }

    /**
     * To test register is successful or not(No information in database)
     */
    @Test
    public void testRegisterinfo(){
        RegisterController rc=new RegisterController();
        boolean temp=rc.register(987654321,"Xiao Ming","xiaoming@gmail.com");
        assertEquals(true,temp);
    }

    /**
     * To test register is successful or not(A part of information in database, name error)
     */
    @Test
    public void testRegisterpartinfo(){
        RegisterController rc=new RegisterController();
        boolean temp=rc.register(123456789,"Xiao Ming","igaryhe@gmail.com");
        assertEquals(true,temp);
    }
}