import enums.BankOperation;
import model.Account;
import model.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Bank bank;

        try(ExecutorService exec = Executors.newFixedThreadPool(4)) {
            bank = new Bank(exec);
            bank.createAccount("Bob", 10000);
            bank.createAccount("Alice", 15500);
            bank.createAccount("Joe", 5000);
            bank.createAccount("Jane", 500);

            simulateClientActivity(bank, 100);
            exec.shutdown();
        }

        System.out.println();
        bank.getTransactionLog().forEach(System.out::println);
    }

    private static void simulateClientActivity(Bank bank, int iterations) {
        Random rand = new Random();

        List<String> accountNumbers = new ArrayList<>(bank.getAccounts().keySet());

        for(int i = 0; i < iterations; i++) {
            // Choose a random account number
            String randomAccountNumber = accountNumbers.get(rand.nextInt(accountNumbers.size()));

            // Get the account associated with the chosen account number
            Account account = bank.getAccounts().get(randomAccountNumber);

            // Choose a bank operation (deposit, withdraw, transfer)
            BankOperation operation = BankOperation.values()[rand.nextInt(2)];

            // Generate an amount of money to use in the transaction
            // In this case it is limited to between $0.0 and $5000.0
            double amount = generateTransactionAmount(0, 5000);

            bank.completeTransaction(account, operation, amount);
        }
    }

    private static double generateTransactionAmount(double min, double max) {
        Random rand = new Random();
        double amount = min + (max - min) * rand.nextDouble();

        return Math.round(amount * 100.0) / 100.0;
    }
}