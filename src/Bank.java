import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Bank {
    private List<Account> accounts;
    private List<Transaction> transactionLog;

    public Bank() {
        accounts = new ArrayList<Account>();
        transactionLog = new ArrayList<Transaction>();
    }

    public List<Account> getAccounts() { return accounts; }
    public List<Transaction> getTransactionLog() { return transactionLog; }
}
