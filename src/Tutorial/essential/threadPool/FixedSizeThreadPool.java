package Tutorial.essential.threadPool;

import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zhaghr on 2018/6/28.
 */

public class FixedSizeThreadPool {
    // task queue
    private BlockingQueue<Runnable> taskQueue;

    // work threads
    private List<Thread> workers;

    // used to
    private volatile boolean working = true;

    public FixedSizeThreadPool(int poolSize, int taskQueueSize) throws InvalidArgumentException {
        if (poolSize <= 0 || taskQueueSize <= 0)
            throw new InvalidArgumentException(new String[]{"invalid "});
        taskQueue = new LinkedBlockingDeque();
        workers = Collections.synchronizedList(new ArrayList<>((int)(poolSize/0.75)+1));
        for(int i=0; i<poolSize; i++){
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    // 直接插入
    public boolean submit(Runnable task){
        return taskQueue.offer(task);
    }

    // 关闭组合的手段：
    // 1. while循环的flag--正常退出
    // 2. 双重判断flag--保证任务全部完成，一次判断可能陷入阻塞
    // 3. 根据flag进行阻塞取或非阻塞取
    // 4. 将等待时的线程中断（wait\sleep\join可以抛InterruptedException）
    public void shutdown(){
        this.working = false;
        for (Thread t : this.workers){
            if (t.getState().equals(Thread.State.BLOCKED) || t.getState().equals(Thread.State.WAITING)
                    || t.getState().equals(Thread.State.TIMED_WAITING)){
                t.interrupt();
            }
        }
    }

    private static class Worker extends Thread{
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool){
            this.pool = pool;
        }

        @Override
        public void run() {
            int taskCount = 0;
            while (pool.working
                    || pool.taskQueue.size() > 0) {
                Runnable task = null;
                try {
                    // 注意在不同状态时，取法的不同
                    if (pool.working)
                        task = pool.taskQueue.take();
                    else
                        task = pool.taskQueue.poll();
                    // 可能task已经被中断
                    if (task != null) {
                        taskCount++;
                        task.run();
                        System.out.println(String.format("%s : task %d is finished.", Thread.currentThread().getName(), taskCount));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
