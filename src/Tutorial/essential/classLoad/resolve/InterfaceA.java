package Tutorial.essential.classLoad.resolve;

public interface InterfaceA {
	public default void print(){
		System.out.println("interface");
	}
	
	public void print(Object object);
}
