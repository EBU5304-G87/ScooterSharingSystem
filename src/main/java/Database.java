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

/**
 * This is a class about database control, file I/O.
 * @author Group 87
 */
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

    /**
     * To new an object.
     */
    public static class DatabaseHolder {
        public static final Database INSTANCE = new Database();
    }

    /**
     * Get information by four functions.
     */
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

    /**
     * Return the object.
     * @return New object of Database.
     */
    public static Database getInstance() {
        return DatabaseHolder.INSTANCE;
    }

    /**
     * Save three types of data use  three functions.
     */
    public void save() {
        writeStations();
        writeRecords();
        writeUsers();
    }

    /**
     * Clear user's data.
     */
    public void clearUserData() {
        userData.removeAll();
    }

    /**
     * Put information into file "Stations"
     */
    private void writeStations() {
        writeFile("Stations", fxGsonOut.toJson(stations));
    }

    /**
     * Put information into file "Users"
     */
    private void writeUsers() {
        writeFile("Users", fxGsonOut.toJson(users));
    }

    /**
     * Put information into file "Records"
     */
    private void writeRecords() {
        writeFile("Records", fxGsonOut.toJson(records));
    }

    /**
     * Read information from "Stations"
     * @throws IOException
     */
    private void readStations() throws IOException {
        stations = fxGsonIn.fromJson(readFile("Stations"), new TypeToken<ArrayList<Station>>(){}.getType());
    }

    /**
     * Read information from "Users"
     * @throws IOException
     */
    private void readUsers() throws IOException {
        users = fxGsonIn.fromJson(readFile("Users"), new TypeToken<ArrayList<User>>(){}.getType());
    }

    /**
     * Read information from "Records"
     * @throws IOException
     */
    private void readRecords() throws IOException {
        records = fxGsonIn.fromJson(readFile("Records"), new TypeToken<ArrayList<Record>>(){}.getType());
    }

    /**
     * Read data from "json" file.
     * @throws IOException
     */
    private void readSchoolUsers() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("SchoolUsers.json").getFile());
        schoolUsers = gsonIn.fromJson(new String(Files.readAllBytes(file.toPath())), SchoolUser[].class);
    }

    /**
     * Put data into file.
     * @param filename file name that will be use
     * @param content content of information
     */
    private void writeFile(String filename, String content) {
        try {
            PrintWriter out = new PrintWriter("src/data/" + filename + ".json");
            out.print(content);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("Write error");
        }
    }

    /**
     * Read information from the file.
     * @param filename file name of the file will be read
     * @return the data get from this file
     * @throws IOException
     */
    private String readFile(String filename) throws IOException {
        return new Scanner(new File("src/data/" + filename + ".json")).useDelimiter("\\Z").next();
    }

    /**
     * Check is it same day.
     * @param date1 First day should be check.
     * @param date2 Second day should be check.
     * @return Is same day or not
     */
    private boolean isSameDay(Date date1, Date date2){
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Get user data and put into a list.
     * @param id user's id
     * @return the list content data of user
     */
    List<Record> getUserRecord(int id) {
        List<Record> list = new ArrayList<>();
        for (Record r : records)
            if (r.getId() == id) list.add(r);
        return list;
    }

    /**
     * Check is total time exceeded.
     * @param id id of user who will be check
     * @return is total time exceeded or not
     */
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