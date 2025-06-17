import java.time.ZonedDateTime;

public class Transaction {
    private final long transactionId;
    private final String destinationAccount;
    private final Task task;
    private final double amount;
    private final ZonedDateTime timeOfTransaction;

    public Transaction(
            long transactionId,
            String destinationAccount,
            Task task,
            double amount
    ) {
        this.transactionId = transactionId;
        this.destinationAccount = destinationAccount;
        this.task = task;
        this.amount = amount;
        this.timeOfTransaction = ZonedDateTime.now();
    }

    @Override
    public String toString() {
        if(task == Task.DEPOSIT) {
            return String.format("%d - %s deposited %.2f at %s", transactionId, destinationAccount, amount, timeOfTransaction);
        } else {
            return String.format("%d - %s withdrew %.2f at %s", transactionId, destinationAccount, amount, timeOfTransaction);
        }
    }
}
