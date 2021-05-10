import java.util.ArrayList;
import java.util.concurrent.Semaphore;
//producer - consumer problem using wait() & notify();
public class WaitNotify {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Semaphore sem = new Semaphore(0);
        Thread[] threads = {
                new Consumer1(list), new Consumer1(list),
                new Producer1(list), new Producer1(list)

        };

        for (var thread: threads) {
            thread.start();
        }


    }
}
class Consumer1 extends Thread{
    final ArrayList<Integer> list;


    public Consumer1(ArrayList<Integer> list) {
        this.list = list;

    }

    @Override
    public void run() {
        System.out.println("consumer runs");
        for (int i = 0; i < 10; i++) {

            while(list.size() == 0){
                synchronized (list) {
                    try {
                        list.wait(); // wait until producer puts sth in the list
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            var item = list.remove(0);
            System.out.println("item " + item + " was removed by " + Thread.currentThread().getId());

        }


    }
}
class Producer1 extends Thread{
    int num = 0;
    final ArrayList<Integer> list;

    public Producer1(ArrayList<Integer> list) {
        this.list = list;

    }
    @Override
    public void run() {
        System.out.println("producer runs");
        for (int i = 0; i < 10; i++) {
            synchronized (list) {
                list.add(num++);
                System.out.println("item " + num + " was produced by " + Thread.currentThread().getId());
                list.notify(); //notify one waiting thread to exit waiting
            }


        }


    }
}
