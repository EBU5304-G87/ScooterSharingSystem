import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class StationController {
    Database db;
    @FXML
    private RadioButton aLight0, aLight1, aLight2, aLight3, aLight4, aLight5, aLight6, aLight7,
            aSlot0, aSlot1, aSlot2, aSlot3, aSlot4, aSlot5, aSlot6, aSlot7,
            aLock0, aLock1, aLock2, aLock3, aLock4, aLock5, aLock6, aLock7,
            bLight0, bLight1, bLight2, bLight3, bLight4, bLight5, bLight6, bLight7,
            bSlot0, bSlot1, bSlot2, bSlot3, bSlot4, bSlot5, bSlot6, bSlot7,
            bLock0, bLock1, bLock2, bLock3, bLock4, bLock5, bLock6, bLock7,
            cLight0, cLight1, cLight2, cLight3, cLight4, cLight5, cLight6, cLight7,
            cSlot0, cSlot1, cSlot2, cSlot3, cSlot4, cSlot5, cSlot6, cSlot7,
            cLock0, cLock1, cLock2, cLock3, cLock4, cLock5, cLock6, cLock7;
    private RadioButton[] aLights, aSlots, aLocks, bLights, bSlots, bLocks, cLights, cSlots, cLocks;
    @FXML
    private Label aLCD, bLCD, cLCD;
    @FXML
    public void initialize() {
        aLights = new RadioButton[] {aLight0, aLight1, aLight2, aLight3, aLight4, aLight5, aLight6, aLight7};
        aSlots = new RadioButton[] {aSlot0, aSlot1, aSlot2, aSlot3, aSlot4, aSlot5, aSlot6, aSlot7};
        aLocks = new RadioButton[] {aLock0, aLock1, aLock2, aLock3, aLock4, aLock5, aLock6, aLock7};
        bLights = new RadioButton[] {bLight0, bLight1, bLight2, bLight3, bLight4, bLight5, bLight6, bLight7};
        bLocks = new RadioButton[] {bLock0, bLock1, bLock2, bLock3, bLock4, bLock5, bLock6, bLock7};
        bSlots = new RadioButton[] {bSlot0, bSlot1, bSlot2, bSlot3, bSlot4, bSlot5, bSlot6, bSlot7};
        cLights = new RadioButton[] {cLight0, cLight1, cLight2, cLight3, cLight4, cLight5, cLight6, cLight7};
        cLocks = new RadioButton[] {cLock0, cLock1, cLock2, cLock3, cLock4, cLock5, cLock6, cLock7};
        cSlots = new RadioButton[] {cSlot0, cSlot1, cSlot2, cSlot3, cSlot4, cSlot5, cSlot6, cSlot7};
        db = Database.getInstance();
        for (int i = 0; i != 8; i++) {
            aLights[i].selectedProperty().bindBidirectional(db.stations.get(0).slots[i].light);
            aLocks[i].selectedProperty().bindBidirectional(db.stations.get(0).slots[i].lock);
            aSlots[i].selectedProperty().bindBidirectional(db.stations.get(0).slots[i].slot);
        }
        for (int i = 0; i != 8; i++) {
            bLights[i].selectedProperty().bindBidirectional(db.stations.get(1).slots[i].light);
            bLocks[i].selectedProperty().bindBidirectional(db.stations.get(1).slots[i].lock);
            bSlots[i].selectedProperty().bindBidirectional(db.stations.get(1).slots[i].slot);
        }
        for (int i = 0; i != 8; i++) {
            cLights[i].selectedProperty().bindBidirectional(db.stations.get(2).slots[i].light);
            cLocks[i].selectedProperty().bindBidirectional(db.stations.get(2).slots[i].lock);
            cSlots[i].selectedProperty().bindBidirectional(db.stations.get(2).slots[i].slot);
        }
        aLCD.textProperty().bindBidirectional(db.stations.get(0).LCD);
        bLCD.textProperty().bindBidirectional(db.stations.get(1).LCD);
        cLCD.textProperty().bindBidirectional(db.stations.get(2).LCD);
    }
}