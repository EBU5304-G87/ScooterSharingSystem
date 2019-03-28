import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
        primaryStage.setTitle("Scooter Sharing System");
        primaryStage.setScene(new Scene(root, 1080, 720));
        primaryStage.show();
    }
}