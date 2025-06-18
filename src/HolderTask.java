/**
 * A {@link #HolderTask} is the {@link Runnable} component of the banking system.
 * It represents the action that takes place when a client makes a transaction into or out
 * of their account. This is designed to be run on a thread allocated by an {@link java.util.concurrent.Executor}
 */
public class HolderTask implements Runnable {
    private final Account account;
    private final double amount;
    private final Task task;

    public HolderTask(Account account, double amount, Task task) {
        this.account = account;
        this.amount = amount;
        this.task = task;
    }

    @Override
    public void run() {
        try {
            if(task == Task.DEPOSIT) {
                account.deposit(amount);
            }
            else if(task == Task.WITHDRAW) {
                account.withdraw(amount);
            }
            else {
                throw new IllegalArgumentException("Invalid task.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }

    }
}
