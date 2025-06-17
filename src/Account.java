public class Account {
    private final String accountName;
    private double balance;

    public Account(String accountName, double balance) {
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

        System.out.printf("%s completed the withdrawal. New Balance: %.2f\n", accountName, balance);
    }

    public synchronized void deposit(double amount) throws IllegalArgumentException {
        if(amount <= 0) { throw new IllegalArgumentException("Deposit must be positive."); }
        balance += amount;
        System.out.printf("%s deposited $%.2f. New Balance: $%.2f\n", accountName, amount, balance);
    }
}
