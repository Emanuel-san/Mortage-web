package app.mortage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MortageTest {
    @Test
    public void testNegativeTotalLoan() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortage("Claes Månsson", -100, 5, 2)
        );

        assertEquals("Loan amount can not be 0 or less", thrown.getMessage());
        thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortage("Claes Månsson", 0, 5, 4)
        );

        assertEquals("Loan amount can not be 0 or less", thrown.getMessage());
    }
    @Test
    public void testNegativeLoanPeriod() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortage("Claes Månsson", 1000, 5, 0)
        );

        assertEquals("Period of years can not be 0 or less", thrown.getMessage());

        thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortage("Claes Månsson", 5600, 2.45, -2)
        );

        assertEquals("Period of years can not be 0 or less", thrown.getMessage());
    }
}