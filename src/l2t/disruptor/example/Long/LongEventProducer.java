package l2t.disruptor.example.Long;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by zhanghr on 2018/3/27.
 */

public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){
        long seq = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(seq);
            event.set(bb.getLong(0));
        }finally {
            ringBuffer.publish(seq);
        }
    }

}
