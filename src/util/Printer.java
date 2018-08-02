package util;

import java.util.List;

public class Printer {
	public static void print(List<?> array){
		for (Object obj:array){
			System.out.print(obj + "\t");
		}
		System.out.println();
	}
	
}
