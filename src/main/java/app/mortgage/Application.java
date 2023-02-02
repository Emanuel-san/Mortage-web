package app.mortgage;

import app.mortgage.handler.DataManager;

import java.nio.file.Path;

public class Application {
    public static void main(String[] args) {
        DataManager manager = new DataManager();
        Path path = Path.of(System.getProperty("user.dir") + "/prospects.txt");
        manager.initDataFetch(path);
        manager.printAllMortgages();
    }
}
