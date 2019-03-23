public class User extends SchoolUser {
    private boolean violation;

    public User(int id, String name, String email) {
        super(id, name, email);
        this.violation = false;
    }

    public boolean isViolation() {
        return violation;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }
}
