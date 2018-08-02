package l2t.interProcessCommunication;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Client {
	public static void main(String[] args) {
		String ip = "127.0.0.1"; //localhost
		Socket socket = null;
		try {
			Collection<Object> list = new LinkedList<>();
			Bean b1 = new Bean(new int[]{1,2,3,4});
			Bean b2 = new Bean(new int[]{5,6,8,9,10});
			list.add(b1);
			list.add(b2);
			socket = new Socket(ip, Server.port);
			System.out.println("client open socket");
			OutputStream os = socket.getOutputStream();
			StreamHandler.writeStream(os, list);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket !=null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("client close!");
	}
}
