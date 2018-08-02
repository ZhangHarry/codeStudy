package lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhanghr on 2018/4/26.
 */

public class SemaphoreOnLock {
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int semaphore = 0;

    public SemaphoreOnLock(int semaphore){
        lock.lock();
        try {
            this.semaphore = semaphore;
        }finally {
            lock.unlock();
        }
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (semaphore == 0)
                condition.await();
            semaphore--;
        }finally {
            lock.unlock();
        }
    }

    public void release(){
        lock.lock();
        try {
            semaphore++;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        Semaphore se = new Semaphore(10);
        se.release(2);
    }

}
