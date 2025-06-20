package model;

/**
 * An {@link #Account} represents a client of the model.Bank. It has {@link #accountName}
 * and {@link #balance} fields. The class handles the logic for withdrawing and depositing
 * money from the client's balance.
 */
public class Account {
    private final String accountNumber;
    private final String accountName;
    private double balance;

    public Account(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = 0;
    }

    public Account(String accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public String getAccountName() { return accountName; }
    public double getBalance() { return balance; }

    public synchronized void withdraw(double amount) throws IllegalArgumentException {
        if(amount <= 0) {
             throw new IllegalArgumentException("Withdrawal must be positive.");
        }
        if(amount > balance) {
            throw new IllegalArgumentException("Cannot withdraw more than the current balance.");
        }

        balance -= amount;
        System.out.printf("%s withdrew $%.2f. New Balance: $%.2f\n", accountName, amount, balance);
    }

    public synchronized void deposit(double amount) throws IllegalArgumentException {
        if(amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive.");
        }

        balance += amount;
        System.out.printf("%s deposited $%.2f. New Balance: $%.2f\n", accountName, amount, balance);
    }

}
