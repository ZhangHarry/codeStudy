package dynamicProxy.rpc.serviceImpl;

import dynamicProxy.rpc.Service;

public class HelloServiceImpl implements Service {
	public String sayHi(String name) {
		return "Hi, " + name;
	}
}
