import enums.BankOperation;
import model.Account;
import operation.Operation;
import operation.OperationFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Bank {
    private final List<Account> accounts;
    private final List<Transaction> transactionLog;
    private final ExecutorService exec;

    public Bank(ExecutorService exec) {
        this.accounts = new ArrayList<>();
        this.transactionLog = new ArrayList<>();
        this.exec = exec;
    }

    public List<Account> getAccounts() { return accounts; }
    public List<Transaction> getTransactionLog() { return transactionLog; }

    public void completeTransaction(Account account, BankOperation bankOperation, double amount) {
        Operation operation = OperationFactory.newBasicOperation(bankOperation, account ,amount);
        // perform the transaction
        exec.execute(operation);
        logTransaction(account, bankOperation, amount);

    }

    public void completeTransaction(
            Account source,
            Account destination,
            BankOperation bankOperation,
            double amount
    ) {
        Operation operation = OperationFactory.newTransferOperation(source, destination, amount);
        exec.execute(operation);
        logTransaction(source, destination, bankOperation, amount);
    }

    private void logTransaction(Account account, BankOperation bankOperation, double amount) {
        transactionLog.add(new Transaction(
                transactionLog.size() + 1,
                account,
                bankOperation,
                amount)
        );
    }

    private void logTransaction(Account source, Account destination, BankOperation bankOperation, double amount) {
        transactionLog.add(new Transaction(
                transactionLog.size() + 1,
                source,
                destination,
                bankOperation,
                amount
        ));
    }
}
