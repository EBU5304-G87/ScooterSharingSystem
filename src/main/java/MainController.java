import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MainController {
    private Database db;
    @FXML
    private List<List<List<RadioButton>>> radios;
    @FXML
    private Label aLCD, bLCD, cLCD;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colName, colEmail;
    @FXML
    private TableColumn<User, Boolean> colViolation;
    @FXML
    private TableView<Record> tableRecord;
    @FXML
    private TableColumn<Record, Integer> colRId;
    @FXML
    private TableColumn<Record, Date> colStime;
    @FXML
    private TableColumn<Record, Date> colEtime;
    @FXML
    private List<TextField> inputs;
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

        colId.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        colViolation.setCellValueFactory(cellData -> cellData.getValue().violation.asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().name);
        colEmail.setCellValueFactory(cellData -> cellData.getValue().email);
        tableUser.setItems(db.userData);

        colRId.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        colStime.setCellValueFactory(cellData -> cellData.getValue().begin);
        colEtime.setCellValueFactory(cellData -> cellData.getValue().end);
        tableRecord.setItems(db.recordData);
        db.recordData.add(new Record(123456789, new Date(), new Date()));
    }

    @FXML
    public void listUsers() {
        db.userData.clear();
        db.userData.addAll(db.users);
    }

    @FXML
    public void listViolatedUsers() {
        db.userData.clear();
        for (User user:db.users) {
            if (user.isViolation()) {
                db.userData.add(user);
            }
        }
    }

    @FXML
    public void openRegWindow(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(root, 400, 200));
            stage.show();
        } catch (IOException e) {
            System.out.println("Cannot found file");
        }
    }

    @FXML
    private void unlockOne() { unlock(0); }

    @FXML
    private void unlockTwo() { unlock(1); }

    @FXML
    private void unlockThree() { unlock(2); }

    private void unlock(int i) {
        Station station = db.stations.get(i);
        try {
            System.out.println(inputs.get(i).getText());
            int id = Integer.parseInt(inputs.get(i).getText());
            for (User user : db.users) {
                if (user.getId() == id) {
                    if (!user.isViolation()) {
                        if (!user.isBorrowed()) {
                            if (station.borrowScooter())
                                station.curUser = user;
                        } else {
                            if (station.returnScooter())
                                station.curUser = user;
                        }
                    } else {
                        station.setLCD("You need to pay the fine");
                    }
                    return;
                }
            }
        } catch (NumberFormatException e) {
            station.setLCD("Invalid id");
        }
        db.stations.get(i).setLCD("Invalid id");
    }

    @FXML
    private void takeOne() { take(0); }

    @FXML
    private void takeTwo() { take(1); }

    @FXML
    private void takeThree() { take(2); }

    private void take(int i) {
        Station station = db.stations.get(i);
        if (station.unlocked != -1) {
            if (station.slots[station.unlocked].slot.get()) {
                // take the scooter
                station.slots[station.unlocked].slot.set(false);
                station.curUser.setBorrowed(true);
                db.records.add(new Record(station.curUser.getId(), new Date(), new Date(0)));
            } else {
                // return the scooter
                station.slots[station.unlocked].slot.set(true);
                // TODO: need to set the return time
                station.curUser.setBorrowed(false);
            }
            station.timer.cancel();
            station.slots[station.unlocked].light.set(false);
            station.slots[station.unlocked].lock.set(true);
            station.setLCD("Locked");
            station.unlocked = -1;
            db.save();
        }
    }
}