package app.mortgage.calculator;

import app.mortgage.Mortgage;

public class MortgageCalculator {

    /**
     * Calculates the monthly payment amount of a mortage based on a total sum, an annual interest and a period in years
     * using formula: E = U[b(1 + b)^p]/[(1 + b)^p - 1]
     * @return Sum of monthly payment
     */
    public static double monthlyPayment(Mortgage mortgage){

        double monthlyInterest = 1;
        // Formula assumes rate is given on a monthly basis and needs to be adjusted from yearly.
        double monthlyInterestRate = mortgage.getAnnualInterestRate()/12/100;

        for(int i = 0; i < mortgage.getLoanPeriod()*12; i++){
            monthlyInterest *= (1 + monthlyInterestRate);
        }

        return mortgage.getTotalLoan() * (monthlyInterestRate * monthlyInterest) / (monthlyInterest - 1);
    }
}
