package operation;

public interface Operation extends Runnable {
    void execute();

    @Override
    default void run() {
        execute();
    }
}