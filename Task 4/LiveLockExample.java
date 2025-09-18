import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LiveLockExample {
    Lock lock1 = new ReentrantLock(true);
    Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        LiveLockExample liveLock = new LiveLockExample();
        new Thread(liveLock::task).start();
        new Thread(liveLock::task).start(); //LifeLock произойдет, если первым будет запущен второй поток (Thread-1)
    }

    private void task() {
        boolean firstLocked = false;
        boolean secondLocked = false;

        try {
            while (!firstLocked || !secondLocked) {
                String name = Thread.currentThread().getName();
                int timeToSleep = Integer.parseInt(name.substring(name.lastIndexOf("-") + 1)) + 1;
                firstLocked = lock1.tryLock();
                System.out.println("First locked:" + firstLocked);
                secondLocked = lock2.tryLock();
                System.out.println("Second locked:" + secondLocked);
                System.out.println(name);
                Thread.sleep(200L * timeToSleep);
            }
            lock1.unlock();
            lock2.unlock();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
