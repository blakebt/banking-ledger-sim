package operation;

import model.Account;

public class TransferOperation implements Operation {
    private final Account source;
    private final Account destination;
    private final double amount;

    public TransferOperation(Account source, Account destination, double amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    @Override
    public void execute() {
        source.withdraw(amount);
        destination.deposit(amount);
    }
}
