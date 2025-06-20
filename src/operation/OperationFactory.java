package operation;

import enums.BankOperation;
import model.Account;

/**
 * Factory class to generate {@link BankOperation} objects based on input
 */
public class OperationFactory {

    /**
     * Creates a new basic operation (deposit or withdraw) to be run on a thread
     *
     * @param type the bank operation
     * @param account the account that the operation will use
     * @param amount the amount of money
     * @return either a {@link DepositOperation} or a {@link WithdrawOperation}
     * @throws IllegalArgumentException if a type that is not BankOperation.DEPOSIT
     * or BankOperation.WITHDRAW
     */
    public static Operation newBasicOperation(
            BankOperation type,
            Account account,
            double amount
    ) throws IllegalArgumentException {

        if(type == BankOperation.DEPOSIT) {
            return new DepositOperation(account, amount);
        }
        if(type == BankOperation.WITHDRAW) {
            return new WithdrawOperation(account, amount);
        }

        throw new IllegalArgumentException("Invalid BankOperation.");
    }

    /**
     * Creates a transfer operation to be run on a thread
     *
     * @param source the account the money is leaving
     * @param destination the account that is receiving the money
     * @param amount the amount of money
     * @return a {@link TransferOperation}
     */
    public static Operation newTransferOperation(
            Account source,
            Account destination,
            double amount
    ) {
        // handle if the source account isn't given
        if(source == null) {
            throw new NullPointerException("Source account cannot be null.");
        }

        return new TransferOperation(source, destination, amount);
    }
}
