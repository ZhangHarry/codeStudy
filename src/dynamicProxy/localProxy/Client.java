package dynamicProxy.localProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		Subject realSubject = new RealSubject();
		InvocationHandler handler = new DynamicProxy(realSubject);

		Subject subject = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(),
				new Class<?>[]{Subject.class}, handler);
		System.out.println(subject.getClass().getName() + "  " + subject.getClass().getInterfaces()[0]);
		subject.rent();
		subject.hello("world");
	}
}
