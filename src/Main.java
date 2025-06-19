import enums.BankOperation;
import model.Account;
import model.Bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Bank bank;
        Account bobAccount = new Account("Bob", 1000.0);
        Account aliceAccount = new Account("Alice", 1500.0);

        try(ExecutorService exec = Executors.newFixedThreadPool(3)) {
            bank = new Bank(exec);
            bank.completeTransaction(bobAccount, BankOperation.DEPOSIT, 100);
            bank.completeTransaction(bobAccount, BankOperation.DEPOSIT, 200);
            bank.completeTransaction(bobAccount, BankOperation.WITHDRAW, 100);
            bank.completeTransaction(bobAccount, BankOperation.DEPOSIT, 300.50);
            bank.completeTransaction(aliceAccount, bobAccount, BankOperation.TRANSFER, 100);
            bank.completeTransaction(bobAccount, BankOperation.WITHDRAW, 501.25);
            bank.completeTransaction(aliceAccount, BankOperation.DEPOSIT, 105.45);
            bank.completeTransaction(aliceAccount, bobAccount, BankOperation.TRANSFER, 100);

            exec.shutdown();
        }

        System.out.println();
        System.out.println(bobAccount.getBalance());
        System.out.println(aliceAccount.getBalance());
        bank.getTransactionLog().forEach(System.out::println);
    }
}