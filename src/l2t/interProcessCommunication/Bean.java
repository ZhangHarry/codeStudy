package l2t.interProcessCommunication;

import java.io.Serializable;

public class Bean implements Serializable{
	String name;
	long count;
	int[] array;
	
	public Bean(int[] array){
		this.count=0;
		this.array = array;
		this.name = array.toString();
		for (int i : array){
			count += i;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s %d : ", name, count));
		for (int i : array) {
			sb.append(i+",");
		}
		return sb.toString();
	}
	
}
