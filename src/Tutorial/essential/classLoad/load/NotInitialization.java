package Tutorial.essential.classLoad.load;

public class NotInitialization {
	public static void main(String[] args) {
		new NotInitialization().testConstant();
	}
	
	public static void testStatic() {
		/**
		 * value是静态字段，只有直接定义了这个字段的父类会被初始化。
		 * 虚拟机规范没有规定子类的加加载和验证，这里子类没有被初始化
		 */
		System.out.println(subClass.value);
	}
	
	public static void testArray() {
		/**
		 * 没有初始化SuperClass，虚拟机内部有个表示数组的类，
		 * newarray指令触发了[LTutorial.essential.classLoad.SuperClass类的初始化，
		 * 没有触发SuperClass的初始化
		 */
		SuperClass[] array = new SuperClass[10];
		System.out.println(array.length);
	}
	
	public void testConstant() {
		/**
		 * static final 基本类型/String 会被放在类文件的常量池中。
		 * 这里在编译阶段的常量传播优化，‘hello’已经被存储在NotInitialization的常量池中
		 */
		System.out.println(SuperClass.HELLO);
		
	}
}
