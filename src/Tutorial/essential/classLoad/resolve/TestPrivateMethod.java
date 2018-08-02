package Tutorial.essential.classLoad.resolve;

/**
 * Created by zhanghr on 2018/7/7.
 */

public class TestPrivateMethod {
    public static void main(String[] args){
        A a = new B();
        a.print();// print 0
    }
}
class A {
    public void print(){       System.out.print(get());    }
    private int get(){        return 0;    }
}
class B extends A{
    // could not override get()
    private int get(){        return 1;    }
}
