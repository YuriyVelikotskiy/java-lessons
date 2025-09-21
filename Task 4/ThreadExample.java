import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadExample {

    final Lock lock = new ReentrantLock(true);
    volatile int temp = 1;

    public static void main(String[] args) {
        ThreadExample threadExample = new ThreadExample();
        new Thread(threadExample::task1).start();
        new Thread(threadExample::task2).start();
    }

    private void task1() {
        while (true) {
            synchronized (lock){
                while (temp != 1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("1");
                temp = 2;
                lock.notifyAll();
            }
        }
    }

    private void task2() {
        while (true) {
            synchronized (lock){
                while (temp != 2){
                    try {
                        lock.wait();
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("2");
                temp = 1;
                lock.notifyAll();
            }
        }
    }
}
