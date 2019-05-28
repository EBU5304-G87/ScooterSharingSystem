/**
 * This class is about control of school user
 * @author Group 87.
 */
public class SchoolUser {
    private int id;
    private String name;
    private String email;

    /**
     * Empty constructor function
     */
    public SchoolUser() {
    }

    /**
     * Constructor function of school user
     * @param id id
     * @param name name
     * @param email email
     */
    public SchoolUser(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get user information
     * @return an object of user has three types information
     */
    public User getUser() {
        return new User(id, name, email);
    }
}
