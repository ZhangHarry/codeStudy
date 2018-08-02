package DesignPattern.CallBack;

/**
 * Created by zhr on 2016/7/29.
 */
public class Test {
    public static void main(String[] args){
        int a =56, b= 46, c = 23243, d = 23423;
        Student student = new Student("xiaoming");
        Seller seller = new Seller("grandma");
        student.callService(a, b);
        seller.callService(c, d);
    }
}
