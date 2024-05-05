package exercises.ch9.ex7;

import java.util.Date;


public class Account {
    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0;
    private final Date dateCreated = new Date();

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * (getMonthlyInterestRate() / 100);
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return String.format("Account #%d\nBalance: %.2f\nMonthly interest: %.2f\nCreated: %s\n",
                id, balance, getMonthlyInterest(), dateCreated.toString()
        );
    }
}
