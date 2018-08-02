package dynamicProxy.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import dynamicProxy.rpc.serviceImpl.HelloServiceImpl;

public class RPCTest {
	public static void main(String[] args) throws IOException {
		// start server
		new Thread(new Runnable() {
			public void run() {
				try {
					Server serviceServer = new ServiceCenter(8088);
					serviceServer.register(Service.class, HelloServiceImpl.class);
					serviceServer.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		// client get remote obj from Proxy
		Service service = RPCClient.getRemoteProxyObj(Service.class,
				new InetSocketAddress("localhost", 8088));
		System.out.println(service.sayHi("test"));
	}
}
