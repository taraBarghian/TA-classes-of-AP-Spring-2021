class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("i'm in thread 1");
    }
}
class Thread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("i'm in thread 2");
    }
}
class Demo {
    public static void main(String[] args) {
        Thread1 th1 = new Thread1();
        Thread th2 = new Thread(new Thread2());
        th1.start();
        th2.start();

    }
}
