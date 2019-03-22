public class User {
    private int id;
    private String name;
    private String email;
    private boolean violation;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.violation = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isViolation() {
        return violation;
    }

    public void setViolation(boolean violation) {
        this.violation = violation;
    }
}
