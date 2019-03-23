import java.util.ArrayList;
import java.util.List;

public class SystemController {
    Database db;
    public SystemController() {
        this.db = Database.getInstance();
    }

    public boolean register(int id) {
        for(SchoolUser schoolUser:db.schoolUsers) {
            if (schoolUser.getId() == id) {
                db.users.add(schoolUser.getUser());
                db.writeUsers();
                return true;
            }
        }
        return false;
    }

    public boolean verifyId(int id) {
        for(User user:db.users) {
            if (user.getId() == id)
                return true;
        }
        return false;
    }

    public User getUser(int id) {
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

    public List<User> getViolatedUsers() {
        List<User> vUsers = new ArrayList<User>();
        for (User user:db.users) {
            if (user.isViolation())
                vUsers.add(user);
        }
        return vUsers;
    }
}