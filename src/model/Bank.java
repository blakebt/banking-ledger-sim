package model;

import enums.BankOperation;
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

    private List<Account> getAccounts() { return accounts; }
    public List<Transaction> getTransactionLog() { return transactionLog; }


    /**
     * Completes a basic ( deposit or withdraw ) transaction. Logs the transaction
     * in the {@link #transactionLog} using
     * {@link #logTransaction(Account, BankOperation, double)}.
     *
     * @param account the account that is being accessed
     * @param bankOperation the specific operation (deposit or withdraw) being performed
     * @param amount the amount of money
     */
    public void completeTransaction(
            Account account,
            BankOperation bankOperation,
            double amount
    ) {
        try {
            Operation operation = OperationFactory.newBasicOperation(bankOperation, account ,amount);
            exec.execute(operation);
            logTransaction(account, bankOperation, amount);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

    }

    /**
     * Completes a transfer transaction. Moving money from one account to another.
     * Logs the transaction in the {@link #transactionLog} using
     * {@link #logTransaction(Account, Account, BankOperation, double)}.
     *
     * @param source the account the money is leaving
     * @param destination the account that is receiving the money
     * @param bankOperation the operation that is being performed (transfer)
     * @param amount the amount of money
     */
    public void completeTransaction(
            Account source,
            Account destination,
            BankOperation bankOperation,
            double amount
    ) {
        try {
            Operation operation = OperationFactory.newTransferOperation(source, destination, amount);
            exec.execute(operation);
            logTransaction(source, destination, bankOperation, amount);
        } catch (NullPointerException e) {
            System.out.println("model.Transaction not completed. Source account cannot be null.");
        }
    }

    /**
     * Logs a basic (deposit or withdraw) transaction in the {@link #transactionLog}.
     *
     * @param account the account that is being accessed
     * @param bankOperation the specific operation (deposit or withdraw) being performed
     * @param amount the amount of money
     */
    private void logTransaction(
            Account account,
            BankOperation bankOperation,
            double amount
    ) {
        transactionLog.add(new Transaction(
                transactionLog.size() + 1,
                account,
                bankOperation,
                amount)
        );
    }

    /**
     * Logs a transfer transaction in the {@link #transactionLog}.
     *
     * @param source the account the money is leaving
     * @param destination the account that is receiving the money
     * @param bankOperation the operation that is being performed (transfer)
     * @param amount the amount of money
     */
    private void logTransaction(
            Account source,
            Account destination,
            BankOperation bankOperation,
            double amount
    ) {
        transactionLog.add(new Transaction(
                transactionLog.size() + 1,
                source,
                destination,
                bankOperation,
                amount
        ));
    }
}
