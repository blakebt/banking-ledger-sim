package operation;

import enums.BankOperation;
import model.Account;

public class OperationFactory {

    public static Operation newBasicOperation(BankOperation type, Account account, double amount) {
        if(type == BankOperation.DEPOSIT) {
            return new DepositOperation(account, amount);
        } else {
            return new WithdrawOperation(account, amount);
        }
    }

    public static Operation newTransferOperation(Account source, Account destination, double amount) {
        return new TransferOperation(source, destination, amount);
    }
}
