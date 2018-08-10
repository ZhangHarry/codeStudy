package Tutorial.essential;

/**
 * Created by zhanghr on 2018/8/10.
 */

public class TestSystemGc {
    // VM options : -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.log
    public static void main(String[] args){
        int[] arrays = new int[100000];
        for (int i=0; i<arrays.length; i++)
            arrays[i] = i;
        arrays = null;
        /**
         * 实验结果表示立即执行了gc
         * java doc里说当该方法调用返回时，JVM已经尽力释放所有空闲空间了
         */
        System.gc();
        arrays = new int[100000];
        for (int i=0; i<arrays.length; i++)
            arrays[i] = i<<1;
    }
}
