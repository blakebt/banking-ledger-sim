import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Account> accounts;
    private List<Transaction> transactionLog;

    public Bank() {
        accounts = new ArrayList<>();
        transactionLog = new ArrayList<>();
    }

    public List<Account> getAccounts() { return accounts; }
    public List<Transaction> getTransactionLog() { return transactionLog; }
}
