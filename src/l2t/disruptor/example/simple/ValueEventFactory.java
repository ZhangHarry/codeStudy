package l2t.disruptor.example.simple;

import com.lmax.disruptor.EventFactory;

/**
 * Created by zhanghr on 2018/3/27.
 */

public class ValueEventFactory implements EventFactory<ValueEvent> {
    private static ValueEventFactory instance = null;

    private ValueEventFactory(){
    }

    public static ValueEventFactory getInstance(){
        if (instance==null){
            synchronized (ValueEventFactory.class){
                if (instance==null)
                    instance = new ValueEventFactory();
            }
        }
        return instance;
    }

    @Override
    public ValueEvent newInstance() {
        return new ValueEvent();
    }
}
