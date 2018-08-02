package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileCopyer {
	public static void main(String[] args) throws IOException {
		String srcDir = "F:\\papaerPJ", target = "D:\\papaerPJ\\";
		File src = new File(srcDir);
		if (src.isDirectory()) {
			File[] files = src.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				String path = file.getName();
				path = path.toLowerCase();
				if (path.charAt(0) - 'a' == 0 && path.charAt(1) - 'r' > 0 && path.charAt(1) - 'z' < 0) {
					String cmd = String.format("xcopy %s %s /e/i", file.getAbsolutePath(), target + file.getName());
					FileCopyer.exec(cmd);
				}
			}
		}
	}

	public static void exec(String cmd) throws IOException {
		String log = "D:\\papaerPJ\\log.txt";
		System.out.format("cmd : %s%n", cmd);
		Runtime run = Runtime.getRuntime();
		OutputStream out = null; 
		try {
			out = new FileOutputStream(new File(log), true); // 是否追加
			Process process = run.exec("cmd.exe /c " + cmd);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk");
			BufferedReader br = new BufferedReader(isr);
			InputStream iserr = process.getErrorStream();
			InputStreamReader isrerr = new InputStreamReader(iserr, "gbk");
			BufferedReader brerr = new BufferedReader(isrerr);
			String s = "";
			while ((s = br.readLine()) != null) {
				byte[] b = s.getBytes();
				out.write(b);
				out.write("\n".getBytes());
			}
			while ((s = brerr.readLine()) != null) {
				System.out.println(s);
			}
			process.waitFor();
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	
	
	public static void exec1(String cmd) throws IOException {
		String log = "D:\\papaerPJ\\log.txt";
		System.out.format("cmd : %s%n", cmd);
		Runtime run = Runtime.getRuntime();
		OutputStream out = null; 
		try {
			out = new FileOutputStream(new File(log), true); // 是否追加
			Process process = run.exec("cmd.exe /c " + cmd);
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "gbk");
			BufferedReader br = new BufferedReader(isr);
			InputStream iserr = process.getErrorStream();
			InputStreamReader isrerr = new InputStreamReader(iserr, "gbk");
			BufferedReader brerr = new BufferedReader(isrerr);
			OutputStream os = process.getOutputStream();
			String s = "";
			
			while ((s = br.readLine()) != null) {
				byte[] b = s.getBytes();
				out.write(b);
				out.write("\n".getBytes());
				if(s.startsWith("Started by \"codescanner\"")){
					os.write("d".getBytes());
					os.flush();
					os.close();
				}
			}
			while ((s = brerr.readLine()) != null) {
				System.out.println(s);
			}
			process.waitFor();
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
