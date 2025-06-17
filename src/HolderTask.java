public class HolderTask implements Runnable {
    private final Account account;
    private final String holderName;
    private final double amount;
    private final Task task;

    public HolderTask(Account account, String holderName, double amount, Task task) {
        this.account = account;
        this.holderName = holderName;
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
