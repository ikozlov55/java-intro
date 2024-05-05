package exercises.ch11.ex8;

import java.util.ArrayList;
import java.util.Date;

/*
    (New Account class) An Account class was specified in Programming Exercise 9.7.
    Design a new Account class as follows:
    ■ Add a new data field name of the String type to store the name of the
    customer.
    ■ Add a new constructor that constructs an account with the specified name, id,
    and balance.
    ■ Add a new data field named transactions whose type is ArrayList that
    stores the transaction for the accounts. Each transaction is an instance of the
    Transaction class, which is defined as shown in Figure 11.6.
    Modify the withdraw and deposit methods to add a transaction to the
    transactions array list.
    ■ All other properties and methods are the same as in Programming Exercise 9.7.
    Programming Exercises 449
    Write a test program that creates an Account with annual interest rate 1.5%,
    balance 1000, id 1122, and name George. Deposit $30, $40, and $50 to the
    account and withdraw $5, $4, and $2 from the account. Print an account summary
    that shows the account holder name, interest rate, balance, and all transactions.
 */
public class Account {
    private int id = 0;

    private String name;
    private double balance = 0;
    private double annualInterestRate = 0;
    private final Date dateCreated = new Date();

    private final ArrayList<Transaction> transactions = new ArrayList<>();

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(int id, double balance, String name) {
        this.id = id;
        this.balance = balance;
        this.name = name;
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
        transactions.add(new Transaction('W', amount, balance, String.format("Withdrawal: -%.2f$", amount)));
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction('D', amount, balance, String.format("Deposit: +%.2f$", amount)));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Account #%d\n", id));
        result.append(String.format("Holder: %s\n", name));
        result.append(String.format("Balance: %.2f\n", balance));
        result.append(String.format("Monthly interest: %.2f\n", getMonthlyInterest()));
        result.append(String.format("Created: %s\n", dateCreated));
        result.append("Transactions: \n");
        for (Transaction t : transactions) {
            result.append(t.getDescription()).append("\n");
        }
        return result.toString();
    }
}
