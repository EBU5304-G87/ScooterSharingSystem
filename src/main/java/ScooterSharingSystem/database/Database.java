package ScooterSharingSystem.database;

import ScooterSharingSystem.models.Record;
import ScooterSharingSystem.models.Station;
import ScooterSharingSystem.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hildan.fxgson.FxGson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Database {
    public Station station;
    private List<User> users;
    private List<Record> records;
    private Gson fxGsonIn;
    private Gson fxGsonOut;

    public static class DatabaseHolder {
        public static final Database INSTANCE = new Database();
    }
    protected Database() {
        fxGsonIn = FxGson.create();
        fxGsonOut = FxGson.coreBuilder().setPrettyPrinting().create();
        try {
            readStations();
            readUsers();
            readRecords();
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }
    public static Database getInstance() {
        return DatabaseHolder.INSTANCE;
    }

    public void save() {
        writeStations();
        writeRecords();
        writeUsers();
    }

    private void writeStations() {
        writeFile("Stations", fxGsonOut.toJson(station));
    }
    private void writeUsers() {
        writeFile("Users", fxGsonOut.toJson(users));
    }
    private void writeRecords() {
        writeFile("Records", fxGsonOut.toJson(records));
    }
    private void readStations() throws IOException {
        station = fxGsonIn.fromJson(readFile("Stations"), Station.class);
    }
    private void readUsers() throws IOException {
        users = fxGsonIn.fromJson(readFile("Users"), new TypeToken<ArrayList<User>>(){}.getType());
    }
    private void readRecords() throws IOException {
        records = fxGsonIn.fromJson(readFile("Records"), new TypeToken<ArrayList<Record>>(){}.getType());
    }

    private void writeFile(String filename, String content) {
        try {
            PrintWriter out = new PrintWriter("data" + File.separator + filename + ".json");
            out.print(content);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Write error");
        }
    }
    private String readFile(String filename) throws IOException {
        return new Scanner(new File("data" + File.separator + filename + ".json")).useDelimiter("\\Z").next();
    }

    protected boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    private List<Record> getUserRecord(int id) {
        List<Record> list = new ArrayList<>();
        for (Record r : records)
            if (r.getId() == id) list.add(r);
        return list;
    }

    public boolean isTotalTimeExceeded(int id) {
        Date today = new Date();
        long totalTime = 0;
        List<Record> userList = getUserRecord(id);
        for (Record r : userList) {
             if(isSameDay(r.getBegin(),today)) {
                 totalTime += (r.getEnd().getTime() - r.getBegin().getTime()) / 60000;
             }
        }
        return totalTime > 120;
    }

    public void unlock(int id) {
        for (User user : users) {
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
        station.setLCD("Invalid id");
    }

    public void take() {
        if (station.unlocked != -1) {
            if (station.slots[station.unlocked].slot.get()) {
                // take the scooter
                station.slots[station.unlocked].slot.set(false);
                station.curUser.setBorrowed(true);
                records.add(new Record(station.curUser.getId(), new Date(), new Date(0)));
            } else {
                // return the scooter
                station.slots[station.unlocked].slot.set(true);
                List<Record> records = getUserRecord(station.curUser.getId());
                Record r = records.get(records.size() - 1);
                r.stopRecord();
                if (r.getIsExceeded() || isTotalTimeExceeded(station.curUser.getId()))
                    station.curUser.setViolation(true);
                station.curUser.setBorrowed(false);
            }
            station.timer.cancel();
            station.slots[station.unlocked].light.set(false);
            station.slots[station.unlocked].lock.set(true);
            station.setLCD("Locked");
            station.unlocked = -1;
            save();
        }
    }
}