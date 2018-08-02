package CAS;

/**
 * Created by Zhanghr on 2016/4/13.
 */
public class Counter {
    private long value = 0;
    public synchronized long getValue(){
        return value;
    }
    public synchronized long increment(){
        return ++value;
    }
}

