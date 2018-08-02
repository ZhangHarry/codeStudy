package l2t.disruptor.example.Long;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhanghr on 2018/3/27.
 */

public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance(){
        return new LongEvent();
    }
}
