package app.mortage.handler;

import app.mortage.exceptions.IllegalArrayLengthException;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DataManagerTest {

    @Test
    void testAddMortage() {
        String[] testData = {"Juha", "1000.2", "1.24", "2"};
        DataManager testManager = new DataManager();

        assertEquals(0, testManager.mapSize());
        try {
            testManager.addMortage(testData);
        } catch (IllegalArrayLengthException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, testManager.mapSize());
    }

    @Test
    void testInitDataFetch() {
        DataManager testManager = new DataManager();
        Path path = Path.of(System.getProperty("user.dir") + "/src/test/java/app/mortage/handler/test_prospects.txt");
        testManager.initDataFetch(path);

        //This is also an indirect test of ID incrementation in Mortage.
        assertEquals(2, testManager.mapSize());
    }

    @Test
    void testFaultyInData(){
        String[] faulty1 = {"Juha", "1000.2", "1.24", "a"};
        String[] faulty2 = {"Juha", "1000.2", "1.24", "2", "2"};
        String[] faulty3 = {"5", "Juha", "1000.2", "1.24", "2", "2"};

        DataManager testManager = new DataManager();
        assertThrows(NumberFormatException.class, () -> testManager.addMortage(faulty1));
        assertThrows(IllegalArrayLengthException.class, () -> testManager.addMortage(faulty2));
        assertThrows(IllegalArrayLengthException.class, () -> testManager.addMortage(faulty3));
    }
}