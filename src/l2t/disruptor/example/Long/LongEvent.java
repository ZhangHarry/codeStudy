package l2t.disruptor.example.Long;

/**
 * Created by zhanghr on 2018/3/27.
 */

public class LongEvent {
    private Long value;

    public void set(long value)
    {
        this.value = value;
    }

    public long getValue(){
        return value;
    }

    public void clear(){
        value = null;
    }
}
