package operation;

import model.Account;

public class WithdrawOperation implements Operation {
    private final Account account;
    private final double amount;

    public WithdrawOperation(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        try {
            account.withdraw(amount);
        } catch (IllegalArgumentException e) {
            System.out.println(account.getAccountName() + " tried to withdraw more than their current balance.");
        }

    }
}
