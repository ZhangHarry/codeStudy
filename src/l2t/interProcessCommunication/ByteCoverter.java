package l2t.interProcessCommunication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ByteCoverter {
	public static byte[] toByteArray(Object obj){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] bytes = new byte[0];
		try {
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			bytes = os.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;		
	}
	
	public static Object toObject(byte[] bytes){
		ByteArrayInputStream is = new ByteArrayInputStream(bytes);
		ObjectInputStream ois;
		Object obj= null;
		try {
			ois = new ObjectInputStream(is);
			obj = ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
