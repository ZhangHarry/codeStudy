package l2t.disruptor.example.human;

/**
 * Created by zhanghr on 2018/4/20.
 */

public class MyData {
    private int id;
    private String value;
    public MyData(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyData [id=" + id + ", value=" + value + "]";
    }
}
