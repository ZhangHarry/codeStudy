package util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zhanghr on 2018/4/23.
 */

public class SystemUtil {

    private static Unsafe unsafe;
    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static Unsafe getUnsafe() {
        return unsafe;
    }

    private static Object helperArray[] = new Object[1];

    public static long getObjectAddress(Unsafe unsafe, Object object){
        helperArray[0] = object;
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        return unsafe.getLong(helperArray, baseOffset);
    }

}
