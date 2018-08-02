package lock;

/**
 * Created by zhanghr on 2018/6/10.
 */

public class TestVolatile {
    int a,b;
    volatile int u,v;
    public void test(){
        int i,j;
        i=a;
        j=b;
        i=v;
        j=u;
        a=i;
        b=j;
    }
}
