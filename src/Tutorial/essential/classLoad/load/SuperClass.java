package Tutorial.essential.classLoad.load;

public class SuperClass {

	static {
		System.out.println("super class init");
	}
	public static int value = 1;
	public static final String HELLO = "hello";
}

class subClass extends SuperClass{
	static {
		System.out.println("sub class init");
	}
}
