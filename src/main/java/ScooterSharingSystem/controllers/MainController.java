package ScooterSharingSystem.controllers;

import ScooterSharingSystem.database.Database;
import ScooterSharingSystem.models.Record;
import ScooterSharingSystem.models.Station;
import ScooterSharingSystem.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Date;
import java.util.List;

/**
 * The interface's control and build
 * @author Group 87
 */
public class MainController {
    private Database db;
    @FXML
    private List<List<List<RadioButton>>> radios;
    @FXML
    private Label aLCD, bLCD, cLCD;
    @FXML
    private List<TextField> inputs;

    /**
     * Initialize the interface.
     */
    @FXML
    public void initialize() {
        db = Database.getInstance();
        for (int i = 0; i != 3; i++)
            for (int j = 0; j != 3; j++)
                for (int k = 0; k != 8; k++)
                    radios.get(i).get(j).get(k).selectedProperty()
                            .bindBidirectional(db.stations.get(i).slots[k].getValue(j));

        aLCD.textProperty().bindBidirectional(db.stations.get(0).LCD);
        bLCD.textProperty().bindBidirectional(db.stations.get(1).LCD);
        cLCD.textProperty().bindBidirectional(db.stations.get(2).LCD);
    }

    /**
     * Unlock
     */
    @FXML
    private void unlockOne() {
        try {
            db.unlock(0, Integer.parseInt(inputs.get(0).getText()));
        } catch (NumberFormatException e) {
            db.stations.get(0).setLCD("Invalid ID");
        }
    }

    /**
     * Unlock
     */
    @FXML
    private void unlockTwo() {
        try {
            db.unlock(1, Integer.parseInt(inputs.get(1).getText()));
        } catch (NumberFormatException e) {
            db.stations.get(1).setLCD("Invalid ID");
        }
    }

    /**
     * Unlock
     */
    @FXML
    private void unlockThree() {
        try {
            db.unlock(2, Integer.parseInt(inputs.get(2).getText()));
        } catch (NumberFormatException e) {
            db.stations.get(2).setLCD("Invalid ID");
        }
    }

    /**
     * Is scooter be taken
     */
    @FXML
    private void takeOne() { db.take(0); }

    /**
     * Is scooter be taken
     */
    @FXML
    private void takeTwo() { db.take(1); }

    /**
     * Is scooter be taken
     */
    @FXML
    private void takeThree() { db.take(2); }
}