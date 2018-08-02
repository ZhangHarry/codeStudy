package l2t.disruptor.example.human;

import com.lmax.disruptor.EventTranslator;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class MyDataEventTranslator implements EventTranslator<MyDataEvent>{
    @Override
    public void translateTo(MyDataEvent event, long sequence) {
        MyData data = new MyData(1, "test");
        event.setData(data);
    }
}
