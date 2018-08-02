package util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Zhanghr on 2016/5/31.
 */
public class CmdExecutor {
	public static void exec(String cmd){

        System.out.format("cmd : %s%n",cmd);
        Runtime run = Runtime.getRuntime();
        if (getOSType().equals("windows"))
            cmd = "cmd.exe /c " + cmd;
        try {
            Process process = run.exec(cmd);
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "gbk");
            BufferedReader br = new BufferedReader(isr);
            InputStream iserr = process.getErrorStream();
            InputStreamReader isrerr = new InputStreamReader(iserr, "gbk");
            BufferedReader brerr = new BufferedReader(isrerr);
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
            while ((s = brerr.readLine()) != null) {
                System.out.println(s);
            }
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getOSType(){
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows"))
            return "windows";
        else if (os.contains("linus"))
            return "linus";
        else if (os.contains("mac"))
            return "mac";
        else
            return "unKnown";
    }
    
    public static void runJava(String className, String classPath, List<String> args){
    	List<String> params = new LinkedList<>();
    	params.add("java");
    	params.add("-cp");
    	params.add(classPath);
    	params.add(className);
    	params.addAll(args);
    	String cmd = String.join(" ", params);
    	exec(cmd);
    	
    }
}
