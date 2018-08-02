package util.tool.vtt;

import util.FileProcesser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhr on 2016/8/12.
 */
public class Resolver {

    public static List<Substitle> read(String path){
        if (!path.endsWith(".vtt")){
            System.err.format("%s is not a .vtt file", path);
            System.exit(-1);
        }
        List<Substitle> substitles = new ArrayList<>();
        String[] substitleItems = FileProcesser.getContent(path).split("\n\n");
        if (!"WEBVTT".equalsIgnoreCase(substitleItems[0])){
            System.err.format("error file form : %s", "file doesn't begin with WEBVTT");
            System.exit(-1);
        }
        for (int i = 1; i < substitleItems.length; i++) {
            String[] item = substitleItems[i].split("\\n");
            try{
                Integer.parseInt(item[0]);
            }catch (NumberFormatException e){
                System.err.format("error file form : %s", e.getMessage());
                System.exit(-1);
            }
            String start = "", end = "";
            StringBuilder subtitleSb = new StringBuilder();
            Pattern p = Pattern.compile("\\d+:\\d+:\\d+\\.\\d+");
            Matcher m = p.matcher(item[1]);
            if(m.find()){
                start = m.group();
            }
            if(m.find()){
                end = m.group();
            }
            for (int j = 2; j < item.length; j++) {
                subtitleSb.append(item[j]+"\n");
            }
            substitles.add(new Substitle(start, end, subtitleSb.toString()));
        }
        return substitles;
    }
    public static void main(String[] args){
        String path =  "G:\\workspace\\codeExercise\\src\\util\\tool\\vtt\\supervised-learning.vtt";
        List<Substitle> substitles = Resolver.read(path);
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("hh:mm:ss.");
        String retStrFormatNowDate = sdFormatter.format("");
    }
}
