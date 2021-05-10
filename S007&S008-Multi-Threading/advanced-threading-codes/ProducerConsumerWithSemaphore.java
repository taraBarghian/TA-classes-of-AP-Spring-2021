import java.util.ArrayList;
import java.util.concurrent.Semaphore;
///producer - consumer problem using Semaphore;
public class ProducerConsumerWithSemaphore {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Semaphore sem = new Semaphore(0);
        Thread[] threads = {
                new Producer(list, sem), new Producer(list, sem),
                new Consumer(list, sem), new Consumer(list, sem)
        };

        for (var thread: threads) {
            thread.start();
        }


    }
}

class Consumer extends Thread{
    final ArrayList<Integer> list;
    private final Semaphore sem;

    public Consumer(ArrayList<Integer> list, Semaphore sem) {
        this.list = list;
        this.sem = sem;
    }

    @Override
    public void run() {
        System.out.println("consumer runs");
        for (int i = 0; i < 10; i++) {
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(list) {
                var item = list.remove(0);
                System.out.println("item " + item + " was removed by " + Thread.currentThread().getId());
            }
        }


    }
}
class Producer extends Thread{
    int num = 0;
    final ArrayList<Integer> list;
    private final Semaphore sem;

    public Producer(ArrayList<Integer> list, Semaphore sem) {
        this.list = list;
        this.sem = sem;
    }
    @Override
    public void run() {
        System.out.println("producer runs");
        for (int i = 0; i < 10; i++) {
            synchronized (list) {
                list.add(num++);
                System.out.println("item " + num + " was produced by " + Thread.currentThread().getId());
            }
            sem.release();

        }


    }
}
