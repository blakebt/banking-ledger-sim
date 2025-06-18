import enums.BankOperation;
import model.Account;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Bank bank;
        Account account = new Account("Bob", 1000.0);
        Account destinationAccount = new Account("Alice", 1500.0);

        try(ExecutorService exec = Executors.newFixedThreadPool(3)) {
            bank = new Bank(exec);
            bank.completeTransaction(account, BankOperation.DEPOSIT, 100);
            bank.completeTransaction(account, BankOperation.DEPOSIT, 200);
            bank.completeTransaction(account, BankOperation.WITHDRAW, 100);
            bank.completeTransaction(account, BankOperation.DEPOSIT, 300);
            bank.completeTransaction(account, BankOperation.WITHDRAW, 500);

            exec.shutdown();
        }

        System.out.println();
        System.out.println(account.getBalance());
        bank.getTransactionLog().forEach(System.out::println);
    }
}