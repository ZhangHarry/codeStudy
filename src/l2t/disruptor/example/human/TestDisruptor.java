package l2t.disruptor.example.human;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class TestDisruptor {
    // Disruptor封装了配置的过程
    public static void main(String[] args) {
        //创建一个执行器(线程池)。
        Executor executor;
        executor = Executors.newFixedThreadPool(4);
        //创建一个Disruptor。
        Disruptor<MyDataEvent> disruptor =
                new Disruptor<MyDataEvent>(new MyDataEventFactory(), 4, executor);

        //创建两个事件处理器。
        MyDataEventHandler handler1 = new MyDataEventHandler();
        KickAssEventHandler handler2 = new KickAssEventHandler();

        //同一个事件，先用handler1处理再用handler2处理。
        disruptor.handleEventsWith(handler1).then(handler2);

        //启动Disruptor。
        disruptor.start();
        //发布10个事件。
        for(int i=0;i<10;i++){
            disruptor.publishEvent(new MyDataEventTranslator());
            System.out.println("发布事件["+i+"]");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
