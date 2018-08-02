package l2t.disruptor.example.human;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class MyDataEventFactory implements EventFactory<MyDataEvent>{
    @Override
    public MyDataEvent newInstance() {
        return new MyDataEvent();
    }
}
