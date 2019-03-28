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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
    SchoolUser[] schoolUsers;
    List<Station> stations;
    List<User> users;
    List<Record> records;
    ObservableList<User> userData;
    ObservableList<Record> recordData;
    private Gson gsonIn;
    private Gson gsonOut;
    private Gson fxGsonIn;
    private Gson fxGsonOut;

    private static class DatabaseHolder {
        private static final Database INSTANCE = new Database();
    }
    private Database() {
        gsonIn = new Gson();
        gsonOut = new GsonBuilder().setPrettyPrinting().create();
        fxGsonIn = FxGson.create();
        fxGsonOut = FxGson.coreBuilder().setPrettyPrinting().create();
        userData = FXCollections.observableArrayList();
        recordData = FXCollections.observableArrayList();
        try {
            readSchoolUsers();
            readStations();
            readUsers();
            readRecords();
        } catch (IOException e) {}
    }
    public static final Database getInstance() {
        return DatabaseHolder.INSTANCE;
    }

    public void saveDatabase() {
        writeStations();
        writeRecords();
        writeUsers();
    }

    public void clearUserData() {
        userData.removeAll();
    }
    void writeStations() {
        writeFile("Stations", fxGsonOut.toJson(stations));
    }
    void writeUsers() {
        writeFile("Users", fxGsonOut.toJson(users));
    }
    void writeRecords() {
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
}