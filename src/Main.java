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
        final int SIM_ITERATIONS = 100; // number of iterations to run in the activity simulation

        // Try to create a fixed number of threads to run operations on
        try(ExecutorService exec = Executors.newFixedThreadPool(4)) {
            bank = new Bank(exec);
            // Create a number of test bank accounts and add them to the bank's
            // account map
            bank.createAccount("Bob", 10000);
            bank.createAccount("Alice", 15500);
            bank.createAccount("Joe", 5000);
            bank.createAccount("Jane", 500);

            simulateClientActivity(bank, SIM_ITERATIONS);

            // safely shutdown the ExecutorService
            exec.shutdown();
        }

        // printing the transaction log from the bank
        System.out.println();
        bank.getTransactionLog().forEach(System.out::println);
    }

    /**
     * Runs a simulation of actions bank account holders could perform. Chooses
     * a random {@link Account}, {@link BankOperation} (deposit, withdraw, transfer), and an amount
     * of money. If the operation chosen is a transfer, a destination account is
     * also generated. The operation is then assigned a thread when one is available
     * and performed on the account(s)
     *
     * @param bank the bank that holds accounts
     * @param iterations number of transactions to simulate between account holders
     */
    private static void simulateClientActivity(Bank bank, int iterations) {
        Random rand = new Random();

        // Get the account numbers of all the accounts in the bank
        // so that one can be chosen randomly
        List<String> accountNumbers = new ArrayList<>(bank.getAccounts().keySet());

        // main simulation logic
        for(int i = 0; i < iterations; i++) {
            // Choose a random account number
            String randomAccountNumber = accountNumbers.get(rand.nextInt(accountNumbers.size()));

            // Get the account associated with the chosen account number
            Account account = bank.getAccounts().get(randomAccountNumber);

            // Choose a bank operation (deposit, withdraw, transfer)
            BankOperation operation = BankOperation.values()[rand.nextInt(3)];

            // Generate an amount of money to use in the transaction
            // In this case it is limited to between $0.0 and $5000.0
            double amount = generateTransactionAmount(0, 5000);

            if(operation == BankOperation.TRANSFER) {
                // If the operation is transfer, we need to generate another account as the
                // destination.
                String destinationAccountNumber;

                // Ensure the destination account is not the source account
                do {
                    destinationAccountNumber = accountNumbers.get(rand.nextInt(accountNumbers.size()));

                } while (destinationAccountNumber.equals(randomAccountNumber));

                Account destinationAccount = bank.getAccounts().get(destinationAccountNumber);

                bank.completeTransaction(account, destinationAccount, operation, amount);

            } else {
                bank.completeTransaction(account, operation, amount);
            }
        }
    }

    /**
     * Generates a random double between two values. Formats the double into the
     * standard format for currency
     *
     * @param min the minimum bound for the random number generation
     * @param max the maximum bound for the random number generation
     *
     * @return amount of money in standard format
     */
    private static double generateTransactionAmount(double min, double max) {
        Random rand = new Random();
        double amount = min + (max - min) * rand.nextDouble();

        // Ensure the amount returned is generated is in the
        // standard format for currency
        return Math.round(amount * 100.0) / 100.0;
    }
}