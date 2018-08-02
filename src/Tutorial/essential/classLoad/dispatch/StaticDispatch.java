package Tutorial.essential.classLoad.dispatch;

public class StaticDispatch {
	static abstract class Human{}
	
	static class Man extends Human{}
	
	static class Woman extends Human{}
	
	public void sayHello(Human human) {
		System.out.println("hi, human");
	}

	public void sayHello(Man man) {
		System.out.println("hi, man");
	}

	public void sayHello(Woman woman) {
		System.out.println("hi, woman");
	}
	
	public static void main(String[] args) {
		/**
		 * static dispatch
		 * 编译器根据参数的静态类型决定使用哪个重载版本
		 * 静态分派的典型是重载
		 */
		Human man = new Man();
		Human woman = new Woman();
		StaticDispatch sr = new StaticDispatch();
		sr.sayHello(man);
		sr.sayHello(woman);
	}
}
