package app.mortgage;

import app.mortgage.calculator.MortgageCalculator;

public class Mortgage {
    private static int mortageCounter = 0;
    private final int id;
    private final String customerName;
    private final double annualInterestRate;
    private final double totalLoan;
    private final int loanPeriod;

    public Mortgage(String customerName, double totalLoan, double annualInterestRate, int loanPeriod) throws IllegalArgumentException{
        if(totalLoan <= 0){
            throw new IllegalArgumentException("Loan amount can not be 0 or less");
        }
        else if(loanPeriod <= 0){
            throw new IllegalArgumentException("Period of years can not be 0 or less");
        }
        this.customerName = customerName;
        this.annualInterestRate = annualInterestRate;
        this.totalLoan = totalLoan;
        this.loanPeriod = loanPeriod;
        this.id = ++mortageCounter;
    }
    public void printCustomerMonthlyPayment(){
        System.out.printf("%s wants to borrow %.2f€ for a period of %d years and pay %.2f€ each month.\n", customerName, totalLoan, loanPeriod, MortgageCalculator.monthlyPayment(this));
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public int getLoanPeriod() {
        return loanPeriod;
    }

    public int getId() {
        return id;
    }
}
