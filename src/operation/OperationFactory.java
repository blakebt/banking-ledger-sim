package operation;

import enums.BankOperation;
import model.Account;

public class OperationFactory {

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

    public static Operation newTransferOperation(
            Account source,
            Account destination,
            double amount
    ) {
        if(source == null) {
            throw new NullPointerException("Source account cannot be null.");
        }

        return new TransferOperation(source, destination, amount);
    }
}
