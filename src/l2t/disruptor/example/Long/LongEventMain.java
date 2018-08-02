package l2t.disruptor.example.Long;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by zhanghr on 2018/3/27.
 */

public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // The factory for the event
        LongEventFactory factory = new LongEventFactory();

        // Specify the size of ring buffer, must be power of 2
        int bufferSize = 1024;

        // Construct the disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, bufferSize, executor);
        // new Disruptor(factory, bufferSize, ProducerType.SINGLE, new BlockingWaitStrategy(), executor);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the disruptor, starts all thread running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer rb = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(rb);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l=0;l<20;l++){
            bb.putLong(0,l);
            producer.onData(bb);
        }

        disruptor.shutdown();
    }
}
