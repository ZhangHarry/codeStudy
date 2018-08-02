package l2t.disruptor.example.Long;

import com.lmax.disruptor.EventHandler;

/**
 * Created by zhanghr on 2018/3/27.
 */

public class LongEventHandler implements EventHandler<LongEvent>{
    public void onEvent(LongEvent event, long seq, boolean endOfBatch){
        System.out.println(event.getValue());
    }
}
