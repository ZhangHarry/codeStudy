package l2t.interProcessCommunication;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;

public class Server {
	public static int port = 9000; 
	public static void main(String[] args) {
		ServerSocket ss = null;
		Socket socket = null;
		try {			
			ss = new ServerSocket(port); // open Socket
			ss.setSoTimeout(50000);// set time out
			socket = ss.accept(); // get connected Socket
			System.out.println("server accept socket");
			InputStream is = socket.getInputStream();
			Collection<Object> result = StreamHandler.readStream(is);
			for (Object obj : result) {
				Bean bean = (Bean) obj;
				System.out.println(bean.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ss != null){
				try {
					ss.close();
					if (socket !=null)
						socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
