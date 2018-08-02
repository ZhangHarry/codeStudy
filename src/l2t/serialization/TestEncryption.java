package l2t.serialization;

import java.io.ObjectOutputStream.PutField;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.ObjectInputStream.GetField;

/**
 * super class must has constructor without parameters if super class does not implement Serializable, otherwise throw exception
 * If so, no-arg constructor of  the first super class will be invoked to create new instance
 */
public class TestEncryption extends Test implements Serializable {
	
	private String password = "pass";

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	/** ObjectOutputStream�����л�ʱ��ͼ�ڱ����л��������Ҳ� ִ��writeObject���� */
	private void writeObject(ObjectOutputStream out){
		try {
			PutField putFields = out.putFields();
			password = Encryption.encode(password);
			System.out.println("encode : " + password);
			putFields.put("password", password);
			out.writeFields();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void readObject(ObjectInputStream in){
		try {
			GetField fields = in.readFields();
			Object obj = fields.get("password", "");
			password = Encryption.decode((String)obj);	
			System.out.println("decode : " + password);		
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			TestEncryption test = new TestEncryption();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(test);
			byte[] bytes = bos.toByteArray();
			out.close();
			
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream in = new ObjectInputStream(bis);
			TestEncryption t = (TestEncryption) in.readObject();
			in.close();
			System.out.println(t.getPassword());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

class Encryption{
	public static String encode(String s){
		return s.toUpperCase();
	}
	
	public static String decode(String s){
		return s.toLowerCase();
	}
}
