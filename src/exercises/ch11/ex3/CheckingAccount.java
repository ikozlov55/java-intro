package exercises.ch11.ex3;

import exercises.ch9.ex7.Account;

public class CheckingAccount extends Account {
    private final double overdraftLimit;
    private double currentOverdraft = 0;

    public CheckingAccount(int id, double balance, double overdraftLimit) {
        super(id, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance() + overdraftLimit - currentOverdraft) {
            System.out.println("Overdraft limit exceeded");
        }
        super.withdraw(amount);
        if (getBalance() < 0) {
            currentOverdraft -= getBalance();
            setBalance(0);
        }
    }

}
