package exercises.ch11.ex3;

import exercises.ch9.ex7.Account;

public class SavingsAccount extends Account {
    public SavingsAccount(int id, double balance) {
        super(id, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance()) {
            System.out.println("Savings account cannot be overdrawn");
        } else {
            super.withdraw(amount);
        }
    }
}
