import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * This class is mainly about register interface and control
 * @author Group 87
 */
public class RegisterController {

    @FXML
    private TextField idField, nameField, emailField;

    /**
     * If user click submit, do these things.
     */
    @FXML
    private void submit() {
        try {
            if (register(Integer.parseInt(idField.getText()), nameField.getText(), emailField.getText())) {
                idField.setText("");
                nameField.setText("");
                emailField.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Look, an Information Dialog");
                alert.setContentText("I have a great message for you!");
                alert.showAndWait();
                Stage stage = ((Stage) idField.getScene().getWindow());
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Information");
                alert.setHeaderText("Invalid");
                alert.setContentText("Ooops, there was an error!");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Information");
            alert.setHeaderText("Invalid");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }

    /**
     * To check is register successful or not
     * @param id input id
     * @param name input name
     * @param email input email
     * @return register successful or not
     */
    private boolean register(int id, String name, String email) {
        Database db = Database.getInstance();
        EmailValidator ev = EmailValidator.getInstance();
        if (!ev.isValid(email) || id / 100000000 == 0)
            return false;
        else {
            for (SchoolUser schoolUser : db.schoolUsers) {
                if (schoolUser.getId() == id && schoolUser.getName().equals(name)) {
                    db.users.add(schoolUser.getUser());
                    db.save();
                    return true;
                }
            }
            return false;
        }
    }
}