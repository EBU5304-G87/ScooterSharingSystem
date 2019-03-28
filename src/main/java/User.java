import javafx.beans.property.*;

public class User {
    BooleanProperty violation;
    IntegerProperty id;
    StringProperty name;
    StringProperty email;

    public User() {
        id = new SimpleIntegerProperty(0);
        name = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        violation = new SimpleBooleanProperty(false);
    }

    public User(int id, String name, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.violation = new SimpleBooleanProperty(false);
    }

    public boolean isViolation() {
        return violation.get();
    }

    public void setViolation(boolean violation) {
        this.violation.set(violation);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}