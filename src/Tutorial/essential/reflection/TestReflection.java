package Tutorial.essential.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

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

		System.out.println("get declared fields : ");
		for (Field f : c.getDeclaredFields()){
			System.out.println(f.getName() + "\t" + Modifier.toString(f.getModifiers()));
		}
		System.out.println();
		System.out.println("get fields : ");
		for (Field f : c.getFields()){
			System.out.println(f.getName() + "\t" + Modifier.toString(f.getModifiers()));
		}
		System.out.println();
		System.out.println("get declared methods : ");
		for (Method m : c.getDeclaredMethods()){
			System.out.println(m.getName() + "\t" + Modifier.toString(m.getModifiers()));
		}
		System.out.println();
		System.out.println("get methods : ");
		for (Method m : c.getMethods()){
			System.out.println(m.getName() + "\t" + Modifier.toString(m.getModifiers()));
		}
	}
}
