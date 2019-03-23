import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Database {
    public Station[] stations;
    public User[] users;
    public Record[] records;
    private Gson gson;

    private static class DatabaseHolder {
        private static final Database INSTANCE = new Database();
    }
    private Database() {
        gson = new Gson();
        try {
            readStations();
            readUsers();
            readRecords();
        } catch (IOException e) {}
    }
    public static final Database getInstance() {
        return DatabaseHolder.INSTANCE;
    }
    public void writeStations() {}
    public void writeUsers() {}
    public void writeRecords() {}
    private void readStations() throws IOException {
        stations = gson.fromJson(readFile("Stations.json"), Station[].class);
    }
    private void readUsers() throws IOException {
        users = gson.fromJson(readFile("Users.json"), User[].class);
    }
    private void readRecords() throws IOException {
        records = gson.fromJson(readFile("Records.json"), Record[].class);
    }
    private void writeFile(String filename, String content) {}
    private String readFile(String filename) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        String content = new String(Files.readAllBytes(file.toPath()));
        return content;
    }
}