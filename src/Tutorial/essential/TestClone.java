package Tutorial.essential;

/**
 * Created by zhanghr on 2018/6/12.
 */

public class TestClone {
    public static void main(String[] args) {
        Person parent = new Person(50, "dad", null);
        Person child = new Person(10, "Mike", parent);
        Person2 child2 = new Person2(10, "Mike", parent);
        try {
            Person cloned = child.clone();
            System.out.println(cloned.parent == child.parent);
            Person2 cloned2 = child2.clone();
            System.out.println(cloned2.parent == child2.parent);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

class Person implements Cloneable{
    int age;
    String name;
    Person parent;
    Person(int age, String name,Person parent){
        this.age = age;
        this.name = name;
        this.parent = parent;
    }

    public Person clone() throws CloneNotSupportedException {
        return (Person)super.clone();
    }
}
class Person2 implements Cloneable{
    int age;
    String name;
    Person parent;
    Person2(int age, String name,Person parent){
        this.age = age;
        this.name = name;
        this.parent = parent;
    }

    Person2(){}

    public Person2 clone() throws CloneNotSupportedException {
        Person2 np = new Person2();
        np.parent = parent.clone();
        return np;
    }
}
