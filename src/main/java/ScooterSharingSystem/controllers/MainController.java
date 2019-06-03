package ScooterSharingSystem.controllers;

import ScooterSharingSystem.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.List;

/**
 * The interface's control and build
 * @author Group 87
 */
public class MainController {
    private Database db;
    @FXML
    private List<List<RadioButton>> a;
    @FXML
    private Label aLCD;
    @FXML
    private TextField input;

    /**
     * Initialize the interface.
     */
    @FXML
    public void initialize() {
        db = Database.getInstance();
        for (int j = 0; j != 3; j++)
            for (int k = 0; k != 8; k++)
                a.get(j).get(k).selectedProperty()
                        .bindBidirectional(db.station.slots[k].getValue(j));
        aLCD.textProperty().bindBidirectional(db.station.LCD);
        db.station.prompt();
    }
    
    /**
     * Unlock
     */
    @FXML
    private void unlock() {
        try {
            db.unlock(Integer.parseInt(input.getText()));
        } catch (NumberFormatException e) {
            db.station.setFailedLCD("Invalid ID");
        }
    }

    /**
     * Is scooter be taken
     */
    @FXML
    private void take() { db.take(); }
}