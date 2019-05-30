package ScooterSharingSystem.database;

import ScooterSharingSystem.models.Record;
import ScooterSharingSystem.models.SchoolUser;
import ScooterSharingSystem.models.Station;
import ScooterSharingSystem.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;

public class Database {
    public SchoolUser[] schoolUsers;
    public List<Station> stations;
    public List<User> users;
    public List<Record> records;
    public ObservableList<User> userData;
    public ObservableList<Record> recordData;
    private Gson gsonIn;
    private Gson fxGsonIn;
    private Gson fxGsonOut;

    public static class DatabaseHolder {
        public static final Database INSTANCE = new Database();
    }
    protected Database() {
        gsonIn = new Gson();
        fxGsonIn = FxGson.create();
        fxGsonOut = FxGson.coreBuilder().setPrettyPrinting().create();
        userData = FXCollections.observableArrayList();
        recordData = FXCollections.observableArrayList();
        try {
            readSchoolUsers();
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
        writeFile("Stations", fxGsonOut.toJson(stations));
    }
    private void writeUsers() {
        writeFile("Users", fxGsonOut.toJson(users));
    }
    private void writeRecords() {
        writeFile("Records", fxGsonOut.toJson(records));
    }
    private void readStations() throws IOException {
        stations = fxGsonIn.fromJson(readFile("Stations"), new TypeToken<ArrayList<Station>>(){}.getType());
    }
    private void readUsers() throws IOException {
        users = fxGsonIn.fromJson(readFile("Users"), new TypeToken<ArrayList<User>>(){}.getType());
    }
    private void readRecords() throws IOException {
        records = fxGsonIn.fromJson(readFile("Records"), new TypeToken<ArrayList<Record>>(){}.getType());
    }

    private void readSchoolUsers() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SchoolUsers.json").getFile());
        schoolUsers = gsonIn.fromJson(new String(Files.readAllBytes(file.toPath())), SchoolUser[].class);
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

    public List<Record> getUserRecord(int id) {
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
}