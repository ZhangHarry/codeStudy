package util;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhanghr on 2017/3/15.
 */
public class Filter {
    public static void filter(File file){
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                boolean bl = false;
                int i = 0;
                for (; i < tempString.length(); i++) {
                    if (tempString.charAt(i) >= '0' && tempString.charAt(i)<='9')
                        bl = true;
                    else if (bl)
                        break;
                }
                if (bl)
                    sb.append(tempString.substring(i)+"\n");
                else
                    sb.append(tempString+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        FileProcesser.saveFile(file.getAbsolutePath(), sb.toString());
    }

    public static void filter(String dir){
        File dirctory = new File(dir);
        if (dirctory.isDirectory()){
            File[] files = dirctory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isFile())
                        return true;
                    else
                        return false;
                }
            });
            for (File file : files)
                filter(file);
        }
    }

    public static void main(String[] args){
        Filter.filter("E:\\workspace\\JPF\\jpf-demo\\Plugin\\src\\");
    }
}
