import enums.BankOperation;
import model.Account;

import java.time.ZonedDateTime;

public class Transaction {
    private final long transactionId;
    private final Account sourceAccount;
    private final Account destinationAccount;
    private final BankOperation bankOperation;
    private final double amount;
    private final ZonedDateTime timeOfTransaction;

    public Transaction(
            long transactionId,
            Account sourceAccount,
            Account destinationAccount,
            BankOperation bankOperation,
            double amount
    ) {
        this.transactionId = transactionId;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.bankOperation = bankOperation;
        this.amount = amount;
        this.timeOfTransaction = ZonedDateTime.now();

    }

    public Transaction(
            long transactionId,
            Account destinationAccount,
            BankOperation bankOperation,
            double amount
    ) {
        this.transactionId = transactionId;
        this.sourceAccount = null;
        this.destinationAccount = destinationAccount;
        this.bankOperation = bankOperation;
        this.amount = amount;
        this.timeOfTransaction = ZonedDateTime.now();
    }

    @Override
    public String toString() {
        switch(bankOperation) {
            case BankOperation.DEPOSIT -> {
                return String.format("%d - %s deposited $%.2f at %s",
                        transactionId,
                        destinationAccount.getAccountName(),
                        amount,
                        timeOfTransaction);
            }
            case BankOperation.WITHDRAW -> {
                return String.format("%d - %s withdrew $%.2f at %s",
                        transactionId,
                        destinationAccount.getAccountName(),
                        amount,
                        timeOfTransaction);
            }
            case BankOperation.TRANSFER -> {
                return String.format("%d - %s transferred $%.2f to %s at %s",
                        transactionId,
                        sourceAccount.getAccountName(),
                        amount,
                        destinationAccount.getAccountName(),
                        timeOfTransaction);
            }
            default -> {
                return "Invalid Bank Operation"; // should never get here
            }
        }
    }
}
