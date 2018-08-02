package Tutorial.essential.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestReflection {
	public static void main(String[] args) throws Exception {
		Class c = Class.forName("Tutorial.essential.reflection.Bean");
		Object obj = c.newInstance();
		Field field = c.getDeclaredField("num");
		field.setAccessible(true);
		field.set(obj, 1);
		Bean bean = (Bean) obj;
		System.out.println(bean.getNum());
		Method method = c.getDeclaredMethod("setNum", int.class);
		method.setAccessible(true);
		method.invoke(obj, 3);
		System.out.println(bean.getNum());
	}
}
