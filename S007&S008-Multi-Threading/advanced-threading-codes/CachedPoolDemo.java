import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CachedPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        Executor e= Executors.newCachedThreadPool();
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                for(int i= 0; i< 4; i++)
                    System.out.println(Thread.currentThread().getId()+":"+i);
            }

        };
        for(int i= 0; i < 3; i++)
            e.execute(runnable);

        //new task gets executed in an old thread
        Thread.sleep(2000);
        e.execute(runnable);
    }
}
