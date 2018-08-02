package CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Zhanghr on 2016/4/13.
 */
public class NonblockingCounter {
    private AtomicInteger value;
    public int getValue(){
        return value.get();
    }
    public int increment(){
        int v;
        do {
            v = value.get();
        }while (!value.compareAndSet(v, v++));
        return v+1;
    }
}
