package l2t.disruptor.example.human;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class MyDataEvent {
    private MyData data;
    public MyData getData() {
        return data;
    }
    public void setData(MyData data) {
        this.data = data;
    }
}
