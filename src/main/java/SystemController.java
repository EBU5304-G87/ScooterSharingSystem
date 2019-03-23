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
}