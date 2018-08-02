package Tutorial.essential;

/**
 * Created by zhanghr on 2018/7/14.
 */

public enum TestEnum {
    Test1("test1");
    String str;
    TestEnum(String s){
        this.str = s;
    }

    public static void main(String[] args){
        System.out.println(TestEnum.Test1);
    }
}
