package app.mortgage.calculator;

import app.mortgage.Mortgage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MortgageCalculatorTest {

    @BeforeEach
    public void clear(){
        Mortgage.resetCounter();
    }
    @ParameterizedTest
    @CsvSource({"Juha,1000,5,2,43.87" , "Karvinen,4356,1.27,6,62.87", "Claes Månsson,1300.55,8.67,2,59.22"})
    public void monthlyPaymentTest(String name, double totalLoan, double annualRate, int period, double expected) {
        Mortgage instance = new Mortgage(name, totalLoan, annualRate, period);
        double roundedValue = MortgageCalculator.round(MortgageCalculator.monthlyPayment(instance), 2);
        System.out.println(roundedValue);
        assertEquals(expected, roundedValue);
    }

    //@ValueSource(doubles = {6.5546, 45.342352, 5678.54523, 12345.000005, 111.555})
    @Test
    public void roundingTest(){
        assertEquals(6.555, MortgageCalculator.round(6.5546,3));
        assertEquals(45.3424, MortgageCalculator.round(45.342352,4));
        assertEquals(5678.6, MortgageCalculator.round(5678.64523,1));
        assertEquals(12345, MortgageCalculator.round(12345.000005,0));
        assertEquals(111, MortgageCalculator.round(111,5));
        assertEquals(-1.567, MortgageCalculator.round(-1.567456,3));
    }
}