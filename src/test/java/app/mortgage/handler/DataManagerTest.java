package app.mortgage.handler;

import app.mortgage.Mortgage;
import app.mortgage.exceptions.IllegalArrayLengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DataManagerTest {

    @BeforeEach
    public void clear(){
        Mortgage.resetCounter();
    }
    @Test
    void testAddMortage() {
        String[] testData = {"Juha", "1000.2", "1.24", "2"};
        DataManager testManager = new DataManager();

        assertEquals(0, testManager.mapSize());
        try {
            testManager.addMortgage(testData);
        } catch (IllegalArrayLengthException | NumberFormatException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1, testManager.mapSize());
    }

    @Test
    void testInitDataFetch() {
        DataManager testManager = new DataManager();
        Path path = Path.of(System.getProperty("user.dir") + "/src/test/java/app/mortgage/handler/test_prospects.txt");
        testManager.initDataFetch(path);

        assertEquals(2, testManager.mapSize());
    }

    @Test
    void testFaultyInData(){
        String[] faulty1 = {"Juha", "11000.2", "1.4", "a"};
        String[] faulty2 = {"Juha", "100.2", "2.24", "2", "2"};
        String[] faulty3 = {"5", "Juha", "1000.2", "5", "2", "2"};

        DataManager testManager = new DataManager();
        assertThrows(NumberFormatException.class, () -> testManager.addMortgage(faulty1));
        assertThrows(IllegalArrayLengthException.class, () -> testManager.addMortgage(faulty2));
        assertThrows(IllegalArrayLengthException.class, () -> testManager.addMortgage(faulty3));
    }
}