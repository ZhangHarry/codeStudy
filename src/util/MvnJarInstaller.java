package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by zhr on 2016/7/18.
 */
public class MvnJarInstaller {

    /**
     * mvn install:install-file -Dfile=D:\mvn\spring-context-support-3.1.0.RELEASE.jar -DgroupId=org.springframework -DartifactId=spring-context-support -Dversion=3.1.0.RELEASE -Dpackaging=jar
     * @param info
     *            <dependency>
     *                <groupId>org.eclipse.core</groupId>
     *                <artifactId>runtime</artifactId>
     *                <version>3.10.0-v20140318-2214</version>
     *            </dependency>
     * @return
     */
    public static void installJar(String info, String filePath){
        info = removeWhiteSpace(info);
        Map attributes = pickAttribute(info);
        StringBuilder sb = new StringBuilder();
        sb.append("mvn install:install-file ");
        sb.append(" -Dfile="+filePath);
        Iterator it = attributes.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry<String, String>)it.next();
            sb.append(" -D"+entry.getKey()+"="+entry.getValue());
        }
        sb.append(" -Dpackaging=jar");
        System.out.println(sb.toString());
        CmdExecutor.exec(sb.toString());
    }



    public static String removeWhiteSpace(String s){
        Pattern p = Pattern.compile("\\s");
        Matcher m=p.matcher(s);
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            m.appendReplacement(sb, "");
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     *
     * @param search
     *       <dependency><groupId>org.eclipse.core</groupId><artifactId>runtime</artifactId><version>3.10.0-v20140318-2214</version></dependency>
     */
    public static Map pickAttribute(String search){

        Pattern pattern ;
        Matcher matcher;
        String regex = "<(\\w+)>[\\w\\.-]+</\\1>";
        Map attributes = new HashMap<String, String>(3);// ["groupId","artifactId", "version"]
        try {
            pattern = Pattern.compile(regex);
            matcher = pattern.matcher(search);
            boolean found = false;
            while (matcher.find()) {
                String content = matcher.group();
                Matcher m =  Pattern.compile("<\\w+>").matcher(content);
                m.find();
                String key = m.group();
                key = key.substring(1, key.length()-1);
                String value =content.replaceAll("(<\\w+>)|(</\\w+>)", "");
//                System.out.format("attribute : %s, value : %s\n", key, value);
                attributes.put(key, value);
                found = true;
            }
            if (!found)
                System.out.println("No match found.%n");
        }catch (PatternSyntaxException pse){
            System.err.format("There is a problem" +
                    " with the regular expression!%n");
            System.err.format("The pattern in question is: %s%n",
                    pse.getPattern());
            System.err.format("The description is: %s%n",
                    pse.getDescription());
            System.err.format("The message is: %s%n",
                    pse.getMessage());
            System.err.format("The index is: %s%n",
                    pse.getIndex());
        }
        return attributes;
    }

    public static void main(String[] args){
        String info = "<dependency>\n" +
                "    <groupId>org.osgi</groupId>\n" +
                "    <artifactId>org.osgi.core</artifactId>\n" +
                "    <version>5.0.0</version>\n" +
                "</dependency>\n";
        MvnJarInstaller.installJar(info, "G:\\Downloads\\org.osgi.core-5.0.0.jar");
    }
}
