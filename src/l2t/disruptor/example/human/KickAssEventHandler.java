package l2t.disruptor.example.human;

import com.lmax.disruptor.EventHandler;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class KickAssEventHandler implements EventHandler<MyDataEvent> {
    @Override
    public void onEvent(MyDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("kick " + event.getData().toString() + ", " + sequence);

    }
}
