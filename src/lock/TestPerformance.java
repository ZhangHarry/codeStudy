package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhanghr on 2018/5/10.
 */

public class TestPerformance {
    public static void main(String[] args){
        int num = 1000;
        for (int i =0; i<10; i++) {
            long startTime = System.currentTimeMillis();
            testSynchronize(num);
            long endTimes = System.currentTimeMillis();
            System.out.println("syn time cost : " + (endTimes - startTime));
            startTime = System.currentTimeMillis();
            testLock(num);
            endTimes = System.currentTimeMillis();
            System.out.println("lock time cost : " + (endTimes - startTime));
        }
    }

    public static void testSynchronize(int num){
        ExecutorService pool = Executors.newFixedThreadPool(num);
        Object obj = new Object();
        for (int i =0; i<num; i++){
            pool.execute(new Sync(obj));
        }
        pool.shutdown();
    }

    public static void testLock(int num){
        ExecutorService pool = Executors.newFixedThreadPool(num);
        Lock obj = new ReentrantLock();
        for (int i =0; i<num; i++){
            pool.execute(new LockJ(obj));
        }
        pool.shutdown();
    }

    public static void job(){
        InversePairs test = new InversePairs();
        int[] array = new int[]{1,2,3,4,6, 234, 234, 345, 3,4, 66,78, 9};
        test.InversePairs(array);
    }
}
class Sync implements Runnable{
    Object obj;
    Sync(Object obj){
        this.obj = obj;
    }

    public void run(){
        synchronized (obj){
            TestPerformance.job();
        }
    }
}

class LockJ implements Runnable{
    Lock lock;
    LockJ(Lock lock){
        this.lock = lock;
    }

    public void run(){
        lock.lock();
        TestPerformance.job();
        lock.unlock();
    }
}

class InversePairs {
    long count = 0;
    public int InversePairs(int [] array) {
        if (array.length <= 1)
            return 0;
        int[]temp = new int[array.length];
        mergeSort(array, temp, 0, array.length-1);
        return (int)(count%1000000007L);
    }

    public void mergeSort(int[] array, int[] temp, int start, int end){
        if (start >= end) {
            return ;
        }
        int median = (start + end)/2;
        mergeSort(array, temp, start, median);
        mergeSort(array, temp, median+1, end);
        merge(array,temp, start, median, median+1, end);
    }

    public void merge(int[] array, int[] temp, int s1, int e1, int s2, int e2){
        int i1 = s1, i2 = s2;
        int i = s1;
        while (i1 <= e1 && i2 <= e2){
            if (array[i1] > array[i2]){
                count += e2-i2+1;
                temp[i++] = array[i1++];
            }else{
                temp[i++] = array[i2++];
            }
        }
        while (i1 <= e1){
            temp[i++] = array[i1++];
        }
        while (i2 <= e2){
            temp[i++] = array[i2++];
        }

        i = s1;
        while (i<=e2) {
            array[i] = temp[i];
            i++;
        }
    }
}
