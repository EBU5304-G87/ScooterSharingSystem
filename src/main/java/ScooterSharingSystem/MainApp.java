package ScooterSharingSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is main function of the project.
 * @author Group 87
 */
public class MainApp extends Application {

    /**
     * Main function
     * @param args Input parameters
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Load and show the main interface.
     * @param primaryStage the interface
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/MainApp.fxml"));
        primaryStage.setTitle("Scooter Sharing System");
        primaryStage.setScene(new Scene(root, 800, 200));
        primaryStage.show();
    }
}