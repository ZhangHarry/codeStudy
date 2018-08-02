package Tutorial.essential;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zhanghr on 2018/4/22.
 */

public class TestString {
    public static void main(String[] args) {
        String a = TestString.intern(2);
        String b="2";
        System.out.println(a==b); // true

    }

    public static String intern(int i){
        Unsafe unsafe = getUnsafe();

        String s = ""+i;
        s.intern();
        // check how intern() store
        System.out.println(getObjectAddr(unsafe, s));
        System.out.println(getObjectAddr(unsafe, s.intern()));

        String ss = "3";
        System.out.println(getObjectAddr(unsafe, ss));

        // does not store string literal when using StringBuilder
        ss = ""+(i+2);
        System.out.println(getObjectAddr(unsafe, ss));
        ss = new String(""+(i+2));
        System.out.println(getObjectAddr(unsafe, ss));
        System.out.println(getObjectAddr(unsafe, ss.intern()));
        return s;
    }

    private static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return unsafe;
    }

    private static long getObjectAddr(Unsafe unsafe, Object obj){
        Object[] helpArray = new Object[1];
        helpArray[0] = obj;
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        return unsafe.getLong(helpArray, baseOffset);
    }

//    public void test(){
//        String s1 = new String("abc");
//        String s2 = new String("abc");
//        String s3 = "abc";
//        System.out.println(s1 == s2); // new object in heap
//        System.out.println(s1 == s3);
//
//        s1 = "abc";
//        s2 = "def";
//        s3 = "abc" + "def";
//        String s4 = s1 + "def";
//        String s5 = "abc" + s2;
//        String s6 = s1 + s2;
//        String s7 = "abcdef";
//        System.out.println(s3 == s7); // 编译期间优化
//        System.out.println(s3 == s4); // 因为是引用，所以没有做优化
//        System.out.println(s3 == s5);
//        System.out.println(s4 == s5);
//        System.out.println(s6 == s5);
//        System.out.println(s6 == s7);
//
//        final String fs = "abc";
//        s6 = fs + "def";
//        System.out.println(s3 == s6); // final可以确定值，可以优化
//    }
}