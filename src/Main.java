import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Account account = new Account("Bob", 1000.0);

        try(ExecutorService exec = Executors.newFixedThreadPool(3)) {
            exec.execute(new HolderTask(account, 100, Task.DEPOSIT));
            bank.getTransactionLog().add(new Transaction(
                    bank.getTransactionLog().size() + 1,
                    account.getAccountName(),
                    Task.DEPOSIT,
                    100)
            );
            exec.execute(new HolderTask(account, 200, Task.WITHDRAW));
            bank.getTransactionLog().add(new Transaction(
                    bank.getTransactionLog().size() + 1,
                    account.getAccountName(),
                    Task.WITHDRAW,
                    200)
            );
            exec.execute(new HolderTask(account, 100, Task.DEPOSIT));
            bank.getTransactionLog().add(new Transaction(
                    bank.getTransactionLog().size() + 1,
                    account.getAccountName(),
                    Task.DEPOSIT,
                    100)
            );
            exec.execute(new HolderTask(account, 100, Task.DEPOSIT));
            bank.getTransactionLog().add(new Transaction(
                    bank.getTransactionLog().size() + 1,
                    account.getAccountName(),
                    Task.DEPOSIT,
                    100)
            );
            exec.execute(new HolderTask(account, 300, Task.WITHDRAW));
            bank.getTransactionLog().add(new Transaction(
                    bank.getTransactionLog().size() + 1,
                    account.getAccountName(),
                    Task.WITHDRAW,
                    300)
            );

            exec.shutdown();
        }

        System.out.println();
        System.out.println(account.getBalance());
        bank.getTransactionLog().forEach(System.out::println);
    }
}