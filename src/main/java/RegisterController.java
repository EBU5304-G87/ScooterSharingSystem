import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private TextField idField, nameField, emailField;

    @FXML
    private void submit() {
        SystemController sc = new SystemController();
        if (sc.register(Integer.parseInt(idField.getText()), nameField.getText(), emailField.getText())) {
            idField.setText("");
            nameField.setText("");
            emailField.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("I have a great message for you!");
            alert.showAndWait();
            Stage stage = ((Stage)idField.getScene().getWindow());
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Information");
            alert.setHeaderText("Invalid");
            alert.setContentText("Ooops, there was an error!");
            alert.showAndWait();
        }
    }
}