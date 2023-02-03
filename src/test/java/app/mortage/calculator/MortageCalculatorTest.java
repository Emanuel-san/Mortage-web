package app.mortage.calculator;

import app.mortage.Mortage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MortageCalculatorTest {

    @ParameterizedTest
    @CsvSource({"Juha,1000,5,2,43.87" , "Karvinen,4356,1.27,6,62.87", "Claes Månsson,1300.55,8.67,2,59.22"})
    public void monthlyPayment(String name, double totalLoan, double annualRate, int period, double expected) {
        Mortage instance = new Mortage(name, totalLoan, annualRate, period);
        double roundedValue = Math.round(MortageCalculator.monthlyPayment(instance) * 100.0) / 100.0;
        assertEquals(roundedValue, expected);
    }
}