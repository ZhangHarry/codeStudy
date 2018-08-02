package Tutorial.essential;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhanghr on 2018/4/23.
 */

public class TestGeneric<T> {

    public T data;

    public TestGeneric(T data) { this.data = data; }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }

    public T get(){
        return data;
    }

//    public boolean equals(T value){
//        return false;
//    }

    public static void main(String[] args){
        List<Fruit> list = new ArrayList<>();
        list.add(new Apple());
        list.add(new Orange());

        List<? extends Fruit> el = new LinkedList<>(list);
//        l.add(new Apple());
//        l.add(new Fruit());
        el.add(null);
        el.get(0);
        System.out.println(new Fruit<String>().getClass() + " " + (new Fruit<Integer>().getClass()));

        List<? super Apple> sl = new LinkedList<>(list);
        sl.get(1);
//        sl.add(new Fruit());
//        Object[] strings = new String[2];
//        strings[0] = "hi";   // OK
//        strings[1] = 100;

        MyNode mn = new MyNode(5);
        TestGeneric n = mn; // A raw type - compiler throws an unchecked warning
        n.setData("Hello"); // Causes a ClassCastException to be thrown.
    }
}

 class MyNode extends TestGeneric<Integer> {
    public MyNode(Integer data) { super(data); }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }

    public Integer get(){
        return this.data;
    }

}

class Fruit<T>{}
class Apple extends Fruit{}
class Orange extends Fruit{}
