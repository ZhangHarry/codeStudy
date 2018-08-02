package Tutorial.essential.classLoad.resolve;

public class Resolution {
	public static void main(String[] args) {
		/**
		 * super classes have higher priority than interfaces
		 */
		new SubClass().print();
	}
}
