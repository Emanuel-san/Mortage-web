package app.mortgage.calculator;

import app.mortgage.Mortgage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MortgageCalculator {

    /**
     * Calculates the monthly payment amount of a mortage based on a total sum, an annual interest and a period in years
     * using formula: E = U[b(1 + b)^p]/[(1 + b)^p - 1]
     * E = Fixed monthly payment
     * b = Interest on a monthly basis
     * U = Total loan
     * p = Number of payments
     * @return Sum of monthly payment
     */
    public static double monthlyPayment(Mortgage mortgage){

        double monthlyInterest = 1;
        // Formula assumes rate is given on a monthly basis and needs to be adjusted from yearly.
        double monthlyInterestRate = mortgage.getAnnualInterestRate()/12/100;

        // m = (1 + b)^p
        for(int i = 0; i < mortgage.getLoanPeriod()*12; i++){
            monthlyInterest *= (1 + monthlyInterestRate);
        }

        // E = U * (b * m) / (m - 1)
        return mortgage.getTotalLoan() * (monthlyInterestRate * monthlyInterest) / (monthlyInterest - 1);
    }

    //Math.round() could be used but in the spirit of the assignment of not using the Math library we instead implement our own.
    /**
     * Round to a given amount of decimals.
     * @param toRound double that will be rounded
     * @param decimals amount of decimals the double will be rounded to
     * @return rounded double
     */
    public static double round(double toRound, int decimals){
        StringBuilder decimalFormatArgument = new StringBuilder("0.");
        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols();
        formatSymbols.setDecimalSeparator('.');

        for(int i = 0; i < decimals; i++){
            decimalFormatArgument.append('0');
        }

        DecimalFormat df = new DecimalFormat(decimalFormatArgument.toString());
        df.setDecimalFormatSymbols(formatSymbols);

        //Round negative numbers
        if(toRound < 0){
            toRound *= (-1);
            return Double.parseDouble(df.format(toRound)) * (-1);
        }
        return Double.parseDouble(df.format(toRound));
    }
}
