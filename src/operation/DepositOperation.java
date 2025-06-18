package operation;

import model.Account;

public class DepositOperation implements Operation {
    private final Account account;
    private final double amount;

    public DepositOperation(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }
}
