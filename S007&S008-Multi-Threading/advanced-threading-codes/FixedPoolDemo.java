import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class FixedPoolDemo {
    public static void main(String[] args) {
        Executor e = Executors.newFixedThreadPool(3);
        Runnable runnable = () -> {
            for (int i = 0; i < 4; i++)
                System.out.println(Thread.currentThread().getId() + ":" + i);
        };
        for(int i= 0; i < 3; i++)
            e.execute(runnable);
    }
}


