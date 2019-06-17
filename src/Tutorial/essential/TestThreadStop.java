package Tutorial.essential;

import org.omg.CORBA.INTERNAL;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhanghr on 2018/8/14.
 */

public class TestThreadStop {
    public static void main(String[] args) throws InterruptedException {
        List<Integer>list =new LinkedList<>();
        Thread thread = new Thread(new MyRun(list));
        thread.start();
        Thread.sleep(1000);
        synchronized (list){
            thread.stop();
        }
        System.out.println("stop " + list.size());
    }
}

class MyRun implements Runnable{
    List<Integer> list;
    public MyRun(List<Integer> list){
        this.list = list;
    }

    @Override
    public void run() {
        int i =0;
        while(true){
            System.out.println(System.currentTimeMillis());
            this.list.add(i++);
            try {
                synchronized (list){
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
