public class SchoolUser {
    protected int id;
    protected String name;
    protected String email;

    public SchoolUser() {
    }

    public SchoolUser(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public User getUser() {
        return new User(id, name, email);
    }
}
