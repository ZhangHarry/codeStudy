package l2t.disruptor.example.human;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class MyDataWorkHandler  implements WorkHandler<MyDataEvent> {

    private String name;

    public MyDataWorkHandler(String name) {
        this.name = name;
    }

    @Override
    public void onEvent(MyDataEvent event) throws Exception {
        System.out.println("WorkHandler[" + name + "]处理事件" + event.getData());
    }

}
