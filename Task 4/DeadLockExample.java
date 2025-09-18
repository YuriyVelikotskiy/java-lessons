import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockExample {
    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        DeadLockExample deadLock = new DeadLockExample();
        new Thread(deadLock::task).start();
        new Thread(deadLock::task).start();
    }

    private void task() {
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            if (lock1.tryLock()) {
                System.out.println("First locked!!!");
                Thread.sleep(20);
                System.out.println("Try locked Second lock!!!");
                lock2.lock();
                System.out.println("Second locked!!!");
            } else {
                lock2.lock();
                System.out.println("Second locked!!!");
                Thread.sleep(20);
                System.out.println("Try locked First lock!!!");
                lock1.lock();
                System.out.println("First locked!!!");
            }
            lock1.unlock();
            lock2.unlock();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
