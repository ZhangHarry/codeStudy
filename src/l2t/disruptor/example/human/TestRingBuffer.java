package l2t.disruptor.example.human;

import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WorkerPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class TestRingBuffer {
    public void testPublish() {
        // 手动为ringbuffer 配置组件
        RingBuffer<MyDataEvent> ringBuffer = RingBuffer.createSingleProducer(new MyDataEventFactory(), 1024);

        // 建立了MyDataEvent数组，但是具体数据没有
        MyDataEvent dataEvent = ringBuffer.get(0);
        System.out.println("Event = " + dataEvent);
        System.out.println("Data = " + dataEvent.getData());

        ringBuffer.publishEvent(new MyDataEventTranslator()); // 使用MyDataEventTranslator发布一次
        dataEvent = ringBuffer.get(0);
        System.out.println("Event = " + dataEvent);
        System.out.println("Data = " + dataEvent.getData());
        dataEvent = ringBuffer.get(1);
        System.out.println("Event = " + dataEvent);
        System.out.println("Data = " + dataEvent.getData());

        // 手动发布事件，next取出可用序列号，取出数组中对应的Event，更新Event，发布该序号
        long sequence = ringBuffer.next();
        MyData data = new MyData(2,"human");
        ringBuffer.get(sequence).setData(data);
        ringBuffer.publish(sequence);
        dataEvent = ringBuffer.get(0);
        System.out.println("Event = " + dataEvent);
        System.out.println("Data = " + dataEvent.getData());
        dataEvent = ringBuffer.get(1);
        System.out.println("Event = " + dataEvent);
        System.out.println("Data = " + dataEvent.getData());

    }

    public void testMulPublish(){
        RingBuffer<MyDataEvent> ringBuffer = RingBuffer.createMultiProducer(new MyDataEventFactory(), 1024);
        final CountDownLatch latch = new CountDownLatch(100);
        for(int i=0;i<100;i++){
            final int index = i;
            //开启多个线程发布事件
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long sequence = ringBuffer.next();
                    MyData data = new MyData(index, sequence+"!");
                    ringBuffer.get(sequence).setData(data);
                    ringBuffer.publish(sequence);
                    latch.countDown();
                }
            }).start();
        }
        try {
            latch.await();
            //最后观察下发布的时间。
            for(int i=0;i<100;i++){
                MyDataEvent event = ringBuffer.get(i);
                System.out.println(event.getData());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void testComsumer(){
        RingBuffer<MyDataEvent> ringBuffer = RingBuffer.createSingleProducer(new MyDataEventFactory(), 1024);

        //创建3个WorkHandler
        MyDataWorkHandler handler1 = new MyDataWorkHandler("1");
        MyDataWorkHandler handler2 = new MyDataWorkHandler("2");
        MyDataWorkHandler handler3 = new MyDataWorkHandler("3");

        // work pool会创建event只会被处理一次的WorkProcessor[]。不同的BatchEventProcessor会处理同样的event
        // WorkPool创建WorkProcessor时，WorkProcessor保证消费者不会重复消费的重点在于：
        //              RingBuffer（event来源），
        //              barrier（分配下一个sequence，主要功劳是对应的sequencer和waitStrategy的相应办法），这里work processor共享同一个barrier，表示它们依赖同样的消费前提
        //              workPool.workSequence被所有work processor共享，避免重复消费
        WorkerPool<MyDataEvent> workerPool =
                new WorkerPool<MyDataEvent>(ringBuffer, ringBuffer.newBarrier(),
                        new IgnoreExceptionHandler(),
                        handler1,handler2,handler3);

        //将WorkPool所有processor的Sequence添加到ringBuffer的监控序列。
        ringBuffer.addGatingSequences(workerPool.getWorkerSequences());

        // 新建线程池来运行work handler
        Executor executor = Executors.newFixedThreadPool(workerPool.getWorkerSequences().length);
        workerPool.start(executor);

        // 发布事件
        for (int index =0;index<100;index++){
            long sequence = ringBuffer.next();
            try{
                MyDataEvent event = ringBuffer.get(sequence);
                MyData data = new MyData(index, index+"s");
                event.setData(data);
            }finally{
                ringBuffer.publish(sequence);
                System.out.println("发布事件["+index+"]");
            }
        }
    }
}
