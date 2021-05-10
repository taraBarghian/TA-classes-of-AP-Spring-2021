public class Incrementer {
    public static void main(String[] args) throws InterruptedException {
        int a = 0, b = 0, c = 0;
        MyThread th1 = new MyThread(a);
        MyThread th2 = new MyThread(b);
        MyThread th3 = new MyThread(c);

        th1.setPriority(Thread.MAX_PRIORITY);
        th2.setPriority(Thread.MIN_PRIORITY);
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.out.println("rest of the code");



    }
}
class MyThread extends Thread{
    int val;
    public MyThread(int val) {
        this.val = val;
    }

    @Override
    public void run() {
        for (int i = 0; i < 11; i++) {
            val++;
            if (val == 5) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("in Thread "+ this.getId() + "-> "+ val);
        }
    }
}
