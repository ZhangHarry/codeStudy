package Tutorial.essential.concurrency;
/**
 *  According to Java Language Specification SE 8, before synchronized method is invoked,
 *  invoker must require lock of the object that contains the method, but it doesn't talk about
 *  resolved variable in the method.
 *  In this case, I check this situation. If exists data race exactly, we will see the count is not equal
 *  to times in following code. This sample couldn't ensure it satisfy exclusive increment exactly, but if it fail,
 *  it must not satisfy exclusive increment. And we exactly observe non-equality , lock of variables doesn't get involved
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhr on 2016/7/27.
 */
public class TestThread {

    static Integer count = 0;

    public static void main(String[] args){
        int times = 70;
        ExecutorService pool;
        count = times;
        while (count == times) {
            pool = Executors.newFixedThreadPool(times);
            count = 0;
            for (int i = 0; i < times; i++) {
                pool.execute(new Increnment());
            }
            pool.shutdown();
            while (!pool.isTerminated());
            System.out.format("max : %d%n", count);
        }
        System.out.println("---------------------------------");
        count = times;
        while (count == times) {
            pool = Executors.newFixedThreadPool(times);
            count = 0;
            for (int i = 0; i < times; i++) {
                pool.execute(new IncrenmentA());
            }
            pool.shutdown();
            while (!pool.isTerminated());
            System.out.format("max : %d%n", count);
        }
    }

    private static class Increnment implements Runnable{

        @Override
        public synchronized void run() {
            String threadName =
                    Thread.currentThread().getName();
            count = count + 1;
//            System.out.format("%s: %d%n",
//                    threadName,
//                    count);
        }
    }


    private static class IncrenmentA implements Runnable{

        @Override
        public void run() {
            String threadName =
                    Thread.currentThread().getName();
            synchronized (count) {
                count = count + 1;
//                System.out.format("%s: %d%n",
//                        threadName,
//                        count);
            }
        }
    }
}



