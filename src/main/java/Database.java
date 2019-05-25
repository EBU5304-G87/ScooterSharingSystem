import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    SchoolUser[] schoolUsers;
    List<Station> stations;
    List<User> users;
    List<Record> records;
    ObservableList<User> userData;
    ObservableList<Record> recordData;
    private Gson gsonIn;
    private Gson fxGsonIn;
    private Gson fxGsonOut;

    public static class DatabaseHolder {
        public static final Database INSTANCE = new Database();
    }
    Database() {
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

    public void clearUserData() {
        userData.removeAll();
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
            PrintWriter out = new PrintWriter("src/data/" + filename + ".json");
            out.print(content);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Write error");
        }
    }
    private String readFile(String filename) throws IOException {
        return new Scanner(new File("src/data/" + filename + ".json")).useDelimiter("\\Z").next();
    }

    private boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    List<Record> getUserRecord(int id) {
        List<Record> list = new ArrayList<>();
        for (Record r : records)
            if (r.getId() == id) list.add(r);
        return list;
    }

    boolean isTotalTimeExceeded(int id) {
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