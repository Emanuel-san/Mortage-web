package app.mortage;

import app.mortage.handler.DataManager;

import java.nio.file.Path;

public class Application {
    public static void main(String[] args) {
        DataManager manager = new DataManager();
        Path path = Path.of(System.getProperty("user.dir") + "/prospects.txt");
        manager.initDataFetch(path);
        manager.printAllMortages();
    }
}
