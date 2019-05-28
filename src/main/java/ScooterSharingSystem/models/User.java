package ScooterSharingSystem.models;

import javafx.beans.property.*;

/**
 * This class is mainly about user's control
 * @author Group 87
 */
public class User {
    public BooleanProperty violation;
    public BooleanProperty borrowed;
    public IntegerProperty id;
    public StringProperty name;
    public StringProperty email;

    /**
     * To set the ini value.
     */
    public User() {
        id = new SimpleIntegerProperty(0);
        name = new SimpleStringProperty("");
        email = new SimpleStringProperty("");
        violation = new SimpleBooleanProperty(false);
        borrowed = new SimpleBooleanProperty(false);
    }

    /**
     * To set ini value of user
     * @param id user's id
     * @param name user's name
     * @param email user's email
     */
    public User(int id, String name, String email) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.violation = new SimpleBooleanProperty(false);
        this.borrowed = new SimpleBooleanProperty(false);
    }

    /**
     * To check is violation
     * @return is or not is
     */
    public boolean isViolation() {
        return violation.get();
    }

    /**
     * To set violation
     * @param violation is or not is
     */
    public void setViolation(boolean violation) {
        this.violation.set(violation);
    }

    /**
     * To check is the scooter borrowed or not
     * @return is or not is
     */
    public boolean isBorrowed(){
        return borrowed.get();
    }

    /**
     * To set the scooter is borrowed or not
     * @param borrowed is or not is
     */
    public void setBorrowed(boolean borrowed){
        this.borrowed.set(borrowed);
    }

    /**
     * To get user's id
     * @return id
     */
    public int getId() {
        return id.get();
    }

    /**
     * To get user's name
     * @return name
     */
    public String getName() {
        return name.get();
    }

    /**
     * To get user's Email
     * @return email
     */
    public String getEmail() {
        return email.get();
    }

    /**
     * To set user's id
     * @param id the id will be set
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     * To set user's name
     * @param name the name will be set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * To set user's email
     * @param email the email will be set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }

    /**
     * Output string about user's information
     * @return string about user's information
     */
    public String toString(){
        return "id: " + id +
                " name: " + name +
                " email: " + email +
                " violation" + violation +
                " borrowed: " + borrowed;
    }
}