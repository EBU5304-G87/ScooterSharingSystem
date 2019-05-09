import javafx.collections.FXCollections;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.ArrayList;
import java.util.List;

public class SystemController {
    Database db;
    public SystemController() {
        this.db = Database.getInstance();
    }

    public boolean register(int id, String name, String email) {
        EmailValidator ev = EmailValidator.getInstance();
        if (!ev.isValid(email) || id / 100000000 == 0)
            return false;
        else {
            for (SchoolUser schoolUser : db.schoolUsers) {
                if (schoolUser.getId() == id && schoolUser.getName().equals(name)) {
                    db.users.add(schoolUser.getUser());
                    db.writeUsers();
                    return true;
                }
            }
            return false;
        }
    }

    public boolean verifyId(int id) {
        for(User user:db.users) {
            if (user.getId() == id)
                return true;
        }
        return false;
    }

    public User getUserById(int id) {
        for (User user:db.users) {
            if (user.getId() == id)
                return user;
        }
        return new User();
    }

    public List<Record> getRecordsForUser(int id) {
        List<Record> tempRecords = new ArrayList<Record>();
        for (Record record:db.records) {
            if (record.getId() == id)
                tempRecords.add(record);
        }
        return tempRecords;
    }

    public void getViolatedUsers() {
        for (User user:db.users) {
            if (user.isViolation())
                db.clearUserData();
                db.userData.add(user);
        }
    }
}