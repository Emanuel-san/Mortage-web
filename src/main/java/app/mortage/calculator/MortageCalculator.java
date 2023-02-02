package app.mortage.calculator;

import app.mortage.Mortage;

public class MortageCalculator {

    /**
     * Calculates the monthly payment amount of a mortage based on a total sum, an annual interest and a period in years
     * using formula: E = U[b(1 + b)^p]/[(1 + b)^p - 1]
     * @return Sum of monthly payment
     */
    public static double monthlyPayment(Mortage mortage){

        double monthlyInterest = 1;
        // Formula assumes rate is given on a monthly basis and needs to be adjusted from yearly.
        double monthlyInterestRate = mortage.getAnnualInterestRate()/12/100;

        for(int i = 0; i < mortage.getLoanPeriod()*12; i++){
            monthlyInterest *= (1 + monthlyInterestRate);
        }

        return mortage.getTotalLoan() * (monthlyInterestRate * monthlyInterest) / (monthlyInterest - 1);
    }
}
