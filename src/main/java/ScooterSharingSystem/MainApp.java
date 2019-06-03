package ScooterSharingSystem;

import ScooterSharingSystem.database.Database;
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
        primaryStage.setScene(new Scene(root, 720, 180));
        primaryStage.show();
    }

    @Override
    public void stop(){
        Database db = Database.getInstance();
        db.save();
        db.station.executor.shutdown();
        db.station.comPort.closePort();
    }
}