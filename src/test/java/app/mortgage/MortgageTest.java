package app.mortgage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MortgageTest {

    @Test
    void mortageIdIncrementing(){
        Mortgage m1 = new Mortgage("Juha", 11000.2, 1.4, 3);
        Mortgage m2 = new Mortgage("Jens", 100.2, 2.24, 4);
        Mortgage m3 = new Mortgage("Juha", 1000.2, 5, 2);

        assertEquals(1, m1.getId());
        assertEquals(2, m2.getId());
        assertEquals(3, m3.getId());

    }
    @Test
    public void testNegativeTotalLoan() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortgage("Claes Månsson", -100, 5, 2)
        );

        assertEquals("Loan amount can not be 0 or less", thrown.getMessage());
        thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortgage("Claes Månsson", 0, 5, 4)
        );

        assertEquals("Loan amount can not be 0 or less", thrown.getMessage());
    }
    @Test
    public void testNegativeLoanPeriod() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortgage("Claes Månsson", 1000, 5, 0)
        );

        assertEquals("Period of years can not be 0 or less", thrown.getMessage());

        thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Mortgage("Claes Månsson", 5600, 2.45, -2)
        );

        assertEquals("Period of years can not be 0 or less", thrown.getMessage());
    }
}