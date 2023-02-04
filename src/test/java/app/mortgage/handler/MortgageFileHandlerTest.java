package app.mortgage.handler;

import app.mortgage.Mortgage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MortgageFileHandlerTest {

    @BeforeEach
    public void clear(){
        Mortgage.resetCounter();
    }

    @Test
    void testReadFile() {
        DataManager testManager = new DataManager();
        MortgageFileHandler testHandler = new MortgageFileHandler(testManager);
        Path path = Path.of(System.getProperty("user.dir") + "/src/test/java/app/mortgage/handler/test_prospects.txt");

        try {
            testHandler.readFile(path);
        } catch (NoSuchFileException e) {
            System.out.println("Could not find test data file: " + e.getMessage());
        }

        // If NoSuchFileException is thrown then this test will fail.
        assertEquals(2, testManager.mapSize());

        Path faultyTestPath = Path.of(System.getProperty("user.dir") + "/src/test/java/app/mortgage/handler/test_prospectssss.txt");
        assertThrows(NoSuchFileException.class, () -> testHandler.readFile(faultyTestPath));
    }
}