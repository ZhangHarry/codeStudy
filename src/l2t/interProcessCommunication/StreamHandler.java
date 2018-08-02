package l2t.interProcessCommunication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class StreamHandler {
	public static Collection<Object> readStream(InputStream is) {
		DataInputStream dis = new DataInputStream(is);
		List<Object> objects = new LinkedList<>();
		try {
			int length = dis.readInt();
			while (length > 0){
				byte[] bytes = new byte[length];
				dis.readFully(bytes);
				length = dis.readInt();
				objects.add(ByteCoverter.toObject(bytes));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return objects;
	}
	
	public static void writeStream(OutputStream os, Collection<Object> objects) {
		DataOutputStream dos = new DataOutputStream(os);
		try {
			for (Object object : objects) {
				byte[] bytes = ByteCoverter.toByteArray(object);
				dos.writeInt(bytes.length);
				dos.write(bytes);
			}
			dos.writeInt(-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
